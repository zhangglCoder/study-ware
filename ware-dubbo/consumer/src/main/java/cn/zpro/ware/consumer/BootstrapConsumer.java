package cn.zpro.ware.consumer;

import cn.zpro.ware.user.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class BootstrapConsumer {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-dubbo.xml"});
        context.start();
        UserService demoService = context.getBean(UserService.class);
        String hello = demoService.hello();
        System.out.println(hello);
    }
}
