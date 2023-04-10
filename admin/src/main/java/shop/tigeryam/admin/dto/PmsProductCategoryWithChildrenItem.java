package shop.tigeryam.admin.dto;

import lombok.Getter;
import lombok.Setter;
import shop.tigeryam.entity.PmsProductCategoryMall;

import java.util.List;

/**
 * 包含子级分类的商品分类
 */
public class PmsProductCategoryWithChildrenItem extends PmsProductCategoryMall {
    @Getter
    @Setter
    private List<PmsProductCategoryMall> children;
}
