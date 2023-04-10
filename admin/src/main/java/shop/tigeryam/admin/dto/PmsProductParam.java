package shop.tigeryam.admin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import shop.tigeryam.entity.*;

import java.util.List;

/**
 * 创建和修改商品的请求参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode
public class PmsProductParam extends PmsProductMall {
    // 商品阶梯价格设置
    private List<PmsProductLadder> productLadderList;
    // 商品满减价格设置
    private List<PmsProductFullReduction> productFullReductionList;
    // 商品会员价格设置
    private List<PmsMemberPrice> memberPriceList;
    // 商品的sku库存信息
    private List<PmsSkuStock> skuStockList;
    // 商品参数及自定义规格属性
    private List<PmsProductAttributeValueMall> productAttributeValueList;
    // 专题和商品关系
    private List<CmsSubjectProductRelation> subjectProductRelationList;
    // 优选专区和商品的关系
    private List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList;
}
