package shop.tigeryam.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import shop.tigeryam.entity.PmsProductAttributeValueMall;

import java.util.List;

/**
 * 商品参数/规格属性自定义Dao
 */
@Repository
public interface PmsProductAttributeValueDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsProductAttributeValueMall> productAttributeValueList);
}
