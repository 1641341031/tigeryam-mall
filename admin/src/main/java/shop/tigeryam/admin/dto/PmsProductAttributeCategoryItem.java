package shop.tigeryam.admin.dto;

import lombok.Getter;
import lombok.Setter;
import shop.tigeryam.entity.PmsProductAttributeCategory;
import shop.tigeryam.entity.PmsProductAttributeMall;

import java.util.List;

/**
 * 带有属性的商品属性分类
 */
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    @Getter
    @Setter
    // 商品属性列表
    private List<PmsProductAttributeMall> productAttributeList;

}
