package shop.tigeryam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author tigeryam
 * @since 2022-12-19
 */
@TableName("pms_product")
public class PmsProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 产品分类id
     */
    private Integer productCategoryId;

    /**
     * 运费模板id
     */
    private Integer feightTemplateId;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 逻辑删除
     */
    private Integer deleteStatus;

    /**
     * 上架状态
     */
    private Integer publishStatus;

    /**
     * 新品状态
     */
    private Integer newStatus;

    /**
     * 推荐状态
     */
    private Integer recommandStatus;

    /**
     * 审核id
     */
    private Integer verifyId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 销量
     */
    private Integer sale;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 促销价
     */
    private BigDecimal promotionPrice;

    /**
     * 属性列表
     */
    private String attributeList;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 宝贝详情
     */
    private String detail;

    /**
     * 相册
     */
    private String image;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableField(exist = false)
    private List<PmsProductStock> productStocks;

    public List<PmsProductStock> getProductStocks() {
        return productStocks;
    }

    public void setProductStocks(List<PmsProductStock> productStocks) {
        this.productStocks = productStocks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
    public Integer getFeightTemplateId() {
        return feightTemplateId;
    }

    public void setFeightTemplateId(Integer feightTemplateId) {
        this.feightTemplateId = feightTemplateId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }
    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }
    public Integer getRecommandStatus() {
        return recommandStatus;
    }

    public void setRecommandStatus(Integer recommandStatus) {
        this.recommandStatus = recommandStatus;
    }
    public Integer getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(Integer verifyId) {
        this.verifyId = verifyId;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
    public String getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(String attributeList) {
        this.attributeList = attributeList;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PmsProduct{" +
            "id=" + id +
            ", brandId=" + brandId +
            ", productCategoryId=" + productCategoryId +
            ", feightTemplateId=" + feightTemplateId +
            ", name=" + name +
            ", deleteStatus=" + deleteStatus +
            ", publishStatus=" + publishStatus +
            ", newStatus=" + newStatus +
            ", recommandStatus=" + recommandStatus +
            ", verifyId=" + verifyId +
            ", sort=" + sort +
            ", sale=" + sale +
            ", price=" + price +
            ", promotionPrice=" + promotionPrice +
            ", attributeList=" + attributeList +
            ", title=" + title +
            ", description=" + description +
            ", keywords=" + keywords +
            ", detail=" + detail +
            ", image=" + image +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
