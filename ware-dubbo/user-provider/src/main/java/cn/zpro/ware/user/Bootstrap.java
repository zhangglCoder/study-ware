package cn.zpro.ware.user;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 启动引导
 * @author zhanggl
 */
public class Bootstrap {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"spring-dubbo.xml"});
        context.start();
        // press any key to exit
        System.in.read();
    }
}
