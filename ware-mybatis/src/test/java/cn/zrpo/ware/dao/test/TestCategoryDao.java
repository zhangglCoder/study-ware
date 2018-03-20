package cn.zrpo.ware.dao.test;


import cn.zpro.ware.dao.CategoryDao;
import cn.zpro.ware.dto.CategoryDto;
import cn.zpro.ware.entity.Category;
import cn.zpro.ware.entity.CategoryExt;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestCategoryDao extends BaseTest {

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
    public void testFindListByParentId2() {
        CategoryDao dao = BaseTest.context.getBean(CategoryDao.class);
        List<CategoryExt> listByParentId = dao.findListByParentId(0);
        print(listByParentId);
    }

//    @Test
//    public void testFindListByParentId() {
//        CategoryDao dao = BaseTest.context.getBean(CategoryDao.class);
//        List<Category> listAll = dao.findListByParentId(0);
//        List<CategoryDto> listDto = new ArrayList<>();
//        listAll.forEach(e->{
//            CategoryDto dto = new CategoryDto();
//            BeanUtils.copyProperties(e,dto);
//            listDto.add(dto);
//        });
//        CategoryDto root = new CategoryDto();
//        for (CategoryDto node1 : listDto) {
//            boolean mark = false;
//            for (CategoryDto node2 : listDto) {
//                if (node1.getParentId() != null && node1.getParentId().equals(node2.getId())) {
//                    mark = true;
//                    node2.getChild().add(node1);
//                    break;
//                }
//            }
//            if (!mark) {
//                root = node1;
//            }
//        }
//        print(root);
//    }

}
