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
}
