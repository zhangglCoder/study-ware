package cn.zpro.ware.dao;

import cn.zpro.ware.entity.Test1;

import java.util.List;

/**
 * @author zhanggl
 */
public interface TestDao {

    /**
     * 查询所有集合
     * @return
     */
    List<Test1> findList();

    /**
     * 添加方法
     * @param test1
     * @return
     */
    Integer add(Test1 test1);
}
