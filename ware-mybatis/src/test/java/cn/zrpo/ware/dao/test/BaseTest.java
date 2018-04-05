package cn.zrpo.ware.dao.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
    public static ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("spring-bean.xml");

    public BaseTest() {
        //github
        //test 1
    }

    /**
     * 打印测试对象
     * @param obj
     */
    public void print(Object obj) {
        System.out.println(JSON.toJSONStringWithDateFormat(obj,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.PrettyFormat));
    }

}
