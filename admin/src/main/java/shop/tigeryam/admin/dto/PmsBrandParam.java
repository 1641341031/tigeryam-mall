package shop.tigeryam.admin.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.tigeryam.admin.validator.FlagValidator;

/**
 * 品牌请求参数
 */
@Data
@EqualsAndHashCode
public class PmsBrandParam {
    @NotEmpty
    // 品牌名称
    private String name;
    // 品牌首字母
    private String firstLetter;
    @Min(value = 0)
    // 排序字段
    private Integer sort;
    @FlagValidator(value = {"0","1"}, message = "厂家状态不正确")
    //是否为厂家制造商
    private Integer factoryStatus;
    @FlagValidator(value = {"0","1"}, message = "显示状态不正确")
    //是否进行显示
    private Integer showStatus;
    @NotEmpty
    //品牌logo"
    private String logo;
    // 品牌大图
    private String bigPic;
    // 品牌故事
    private String brandStory;
}
