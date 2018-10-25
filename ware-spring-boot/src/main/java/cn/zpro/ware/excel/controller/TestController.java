package cn.zpro.ware.excel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zhanggl
 */
@RestController
public class TestController {
    public static Integer total = 100;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("haha");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    @GetMapping("index")
    public ModelAndView test(ModelAndView modelAndView) throws BlockException {
        initFlowRules();
        Entry entry = null;
        // 务必保证finally会被执行
        try {
            // 资源名可使用任意有业务语义的字符串
            entry = SphU.entry("haha");
            /**
             * 被保护的业务逻辑
             */
        } catch (BlockException e1) {
            // 资源访问阻止，被限流或被降级
            e1.printStackTrace();
            // 进行相应的处理操作
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        modelAndView.setViewName("index");
        System.out.println("请求成功");
        return modelAndView;
    }

    @GetMapping("ts")
    public String ts() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        long start = System.currentTimeMillis();
        threadPoolTaskExecutor.execute(()->{
            try {
                task1();
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPoolTaskExecutor.execute(()->{
            try {
                task1();
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        return "检查成功";
    }

    public void task1() throws InterruptedException {
        Thread.sleep(5000L);
        System.out.println("任务检查完成");
    }

    @GetMapping("test")
    public String test() {
        return "测试成功!";
    }

    @PostMapping("sub")
    public ModelAndView sub(ModelAndView modelAndView) throws InterruptedException {
        threadPoolTaskExecutor.execute(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        modelAndView.setViewName("success");
        return modelAndView;
    }

//
//    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
//
//            executor.setKeepAliveTime(10, TimeUnit.SECONDS);
//
//            executor.allowCoreThreadTimeOut(true);

//    public static final ExecutorService EXECUTOR_SERVICE =  Executors.newFixedThreadPool(5);
    @PostMapping("task")
    public ModelAndView task(ModelAndView modelAndView) throws InterruptedException {
        threadPoolTaskExecutor.execute(()->{
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getId()+"+++"+Thread.currentThread().getName());
                System.out.println(1111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPoolTaskExecutor.execute(()->{
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getId()+"+++"+Thread.currentThread().getName());
                System.out.println(2222);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPoolTaskExecutor.execute(()->{
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getId()+"+++"+Thread.currentThread().getName());
                System.out.println(3333);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        modelAndView.setViewName("success");
        return modelAndView;
    }


}
