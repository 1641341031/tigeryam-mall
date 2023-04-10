package shop.tigeryam.admin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类对应属性信息
 * Created by macro on 2018/5/23.
 */
@Data
@EqualsAndHashCode
public class ProductAttrInfo {
    // 商品属性ID
    private Long attributeId;
    // 商品属性分类ID
    private Long attributeCategoryId;
}
