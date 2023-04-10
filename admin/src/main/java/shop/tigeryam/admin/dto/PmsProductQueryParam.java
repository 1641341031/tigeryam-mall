package shop.tigeryam.admin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PmsProductQueryParam {
    private Integer publishStatus;
    private Integer verifyStatus;
    private String keyword;
    private String productSn;
    private Long productCategoryId;
    private Long brandId;
}
