package cn.zpro.ware.dao;

import cn.zpro.ware.entity.Category;
import cn.zpro.ware.entity.CategoryExt;

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
    List<CategoryExt> findListByParentId(Integer parentId);
}
