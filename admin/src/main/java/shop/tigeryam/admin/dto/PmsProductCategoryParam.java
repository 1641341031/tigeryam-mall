package shop.tigeryam.admin.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.tigeryam.admin.validator.FlagValidator;

import java.util.List;

/**
 * 添加更新产品分类的参数
 */
@Data
@EqualsAndHashCode
public class PmsProductCategoryParam {
    // 父分类的编号
    private Long parentId;
    @NotEmpty
    // 商品分类名称
    private String name;
    // 分类单位
    private String productUnit;
    @FlagValidator(value = {"0","1"},message = "状态只能为0或1")
    // 是否在导航栏显示
    private Integer navStatus;
    @FlagValidator(value = {"0","1"},message = "状态只能为0或1")
    // 是否进行显示
    private Integer showStatus;
    @Min(value = 0)
    // 排序
    private Integer sort;
    // 图标
    private String icon;
    // 关键字
    private String keywords;
    // 描述
    private String description;
    // 产品相关筛选属性集合
    private List<Long> productAttributeIdList;
}
