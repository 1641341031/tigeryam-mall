package shop.tigeryam.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import shop.tigeryam.admin.dto.ProductAttrInfo;

import java.util.List;

/**
 * 商品属性管理自定义Dao
 * Created by macro on 2018/5/23.
 */
@Repository
public interface PmsProductAttributeDao {
    /**
     * 获取商品属性信息
     */
    List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);
}
