package cn.zpro.ware.dto;

import cn.zpro.ware.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto extends Category {

   private List<Category> child = new ArrayList<>();

    public List<Category> getChild() {
        return child;
    }

    public void setChild(List<Category> child) {
        this.child = child;
    }
}
