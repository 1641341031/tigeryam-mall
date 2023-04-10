package shop.tigeryam.mapper;

import org.springframework.stereotype.Repository;
import shop.tigeryam.entity.PmsSkuStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * sku的库存 Mapper 接口
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
@Repository
public interface PmsSkuStockMapper extends BaseMapper<PmsSkuStock> {

}
