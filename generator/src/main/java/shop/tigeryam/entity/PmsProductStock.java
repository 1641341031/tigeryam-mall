package shop.tigeryam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author tigeryam
 * @since 2022-12-29
 */
@TableName("pms_product_stock")
public class PmsProductStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 库存产品名称
     */
    private String name;

    /**
     * 产品id
     */
    private Integer productId;

    private String image;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 库存预警
     */
    private Integer lowStock;

    /**
     * 产品单位
     */
    private String unit;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 产品规格
     */
    private String productSpecs;

    /**
     * 条码
     */
    private String barcode;

    /**
     * 乐观锁
     */
    private Integer version;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Integer getLowStock() {
        return lowStock;
    }

    public void setLowStock(Integer lowStock) {
        this.lowStock = lowStock;
    }
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getProductSpecs() {
        return productSpecs;
    }

    public void setProductSpecs(String productSpecs) {
        this.productSpecs = productSpecs;
    }
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "PmsProductStock{" +
            "id=" + id +
            ", name=" + name +
            ", productId=" + productId +
            ", image=" + image +
            ", stock=" + stock +
            ", lowStock=" + lowStock +
            ", unit=" + unit +
            ", price=" + price +
            ", productSpecs=" + productSpecs +
            ", barcode=" + barcode +
            ", version=" + version +
            ", description=" + description +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
