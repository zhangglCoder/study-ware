package cn.zrpo.ware.dao.test;


import cn.zpro.ware.entity.Test1;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDao {

    private static SqlSessionFactory getSqlSessionFactory(){
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void testFindList(){
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        List<Test1> list = session.selectList("cn.zpro.ware.dao.TestDao.findList",Test1.class);
        System.out.println(list);
    }
}
