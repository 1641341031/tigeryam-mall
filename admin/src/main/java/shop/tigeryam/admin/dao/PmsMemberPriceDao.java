package shop.tigeryam.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import shop.tigeryam.entity.PmsMemberPrice;

import java.util.List;

/**
 * 会员价格管理自定义Dao
 */
@Repository
public interface PmsMemberPriceDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsMemberPrice> memberPriceList);
}
