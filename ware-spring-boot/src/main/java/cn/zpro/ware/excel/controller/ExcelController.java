package cn.zpro.ware.excel.controller;

import cn.zpro.ware.excel.model.ExcelPropertyIndexModel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhanggl
 */
@RestController
public class ExcelController {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @GetMapping("index")
    public ModelAndView test(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @PostMapping("test2")
    @ResponseBody
    public ResponseEntity<byte[]> test2() throws FileNotFoundException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        threadPoolTaskExecutor.execute(()->{
            try (OutputStream out = new FileOutputStream("F:/77.xlsx")) {
                ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
                Sheet sheet1 = new Sheet(1, 0, ExcelPropertyIndexModel.class);
                sheet1.setSheetName("第一个sheet");
                writer.write(getData(), sheet1);
                writer.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Object call = threadPoolTaskExecutor.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    System.out.println("call");
                    ResponseEntity<byte[]> responseEntity = fileDownLoad();
                    return responseEntity;
                }
            }).get();

            return (ResponseEntity<byte[]>)call;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    @PostMapping("test")
    @ResponseBody
    public ResponseEntity<byte[]> test() throws FileNotFoundException {

        ResponseEntity<byte[]> responseEntity = null;

        threadPoolTaskExecutor.execute(() -> {
            try (OutputStream out = new FileOutputStream("F:/77.xlsx")) {
                ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
                Sheet sheet1 = new Sheet(1, 0, ExcelPropertyIndexModel.class);
                sheet1.setSheetName("第一个sheet");
                writer.write(getData(), sheet1);
                writer.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        threadPoolTaskExecutor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return fileDownLoad();
            }

        });

        return null;
    }

    private ResponseEntity<byte[]> fileDownLoad() throws Exception {

        String fileName = "77.xlsx";
        InputStream in = new FileInputStream(new File("F:/77.xlsx"));
        //将该文件加入到输入流之中
        byte[] body = new byte[in.available()];
        // 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
        in.read(body);
        //读入到输入流里面

        //防止中文乱码
        fileName = new String(fileName.getBytes("utf-8"), "iso8859-1");
        //设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + fileName);
        HttpStatus statusCode = HttpStatus.OK;
        //设置响应吗
        return new ResponseEntity<>(body, headers, statusCode);
    }

    private List<ExcelPropertyIndexModel> getData() {

        List<ExcelPropertyIndexModel> datas = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            ExcelPropertyIndexModel model = new ExcelPropertyIndexModel();
            model.setAddress("杭州" + i);
            model.setAge("11" + i);
            model.setEmail("7827323@qq.com");
            model.setSax("男");
            model.setHeigh("1123");
            model.setName("zhanggl" + i);
            datas.add(model);
        }
        return datas;
    }

    /**
     * 对文件流输出下载的中文文件名进行编码 屏蔽各种浏览器版本的差异性
     */
    public static String encodeChineseDownloadFileName(
            HttpServletRequest request, String pFileName) throws Exception {

        String filename = null;
        String agent = request.getHeader("USER-AGENT");
        if (null != agent) {
            if (-1 != agent.indexOf("Firefox")) {
                //Firefox
                filename = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(pFileName.getBytes("UTF-8")))) + "?=";
            } else if (-1 != agent.indexOf("Chrome")) {
                //Chrome
                filename = new String(pFileName.getBytes(), "ISO8859-1");
            } else {//IE7+
                filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
                filename = filename.replace("+", "%20");
            }
        } else {
            filename = pFileName;
        }
        return filename;
    }

}
