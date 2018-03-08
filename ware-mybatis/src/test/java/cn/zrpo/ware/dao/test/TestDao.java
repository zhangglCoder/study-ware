package cn.zrpo.ware.dao.test;


import cn.zpro.ware.entity.SexEum;
import cn.zpro.ware.entity.Test1;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestDao extends BaseTest {

    private static SqlSessionFactory getSqlSessionFactory() {
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
    public void testFindList() {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        List<Test1> list = session.selectList("cn.zpro.ware.dao.TestDao.findList", Test1.class);
        print(list);
    }

    @Test
    public void testAdd() {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        Test1 test1 = new Test1();
        test1.setId(30);
        test1.setSex(SexEum.MAN);
        int rowCount = session.insert("cn.zpro.ware.dao.TestDao.insert", test1);
        session.commit();
        System.out.println(rowCount);
    }

    @Test
    public void testSpring2() {
        cn.zpro.ware.dao.TestDao dao = BaseTest.context.getBean(cn.zpro.ware.dao.TestDao.class);
        Test1 test1 = new Test1();
        test1.setId(35);
        test1.setSex(SexEum.MAN);
        test1.setCreatTime(new Date());
        print(dao.insert(test1));
    }

    @Test
    public void testSpring() {
        cn.zpro.ware.dao.TestDao dao = BaseTest.context.getBean(cn.zpro.ware.dao.TestDao.class);
        print(dao.findList());
    }
}
