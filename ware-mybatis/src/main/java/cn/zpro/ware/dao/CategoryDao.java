package cn.zpro.ware.dao;

import cn.zpro.ware.entity.Category;

import java.util.List;

/**
 * @author zhanggl
 */
public interface CategoryDao {

    /**
     * 查询parentId下所有的节点
     * @param parentId
     * @return
     */
    List<Category> findListByParentId(Integer parentId);
}
