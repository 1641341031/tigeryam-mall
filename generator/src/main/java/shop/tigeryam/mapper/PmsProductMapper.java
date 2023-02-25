package shop.tigeryam.mapper;

import org.springframework.stereotype.Component;
import shop.tigeryam.entity.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import shop.tigeryam.entity.PmsProductStock;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tigeryam
 * @since 2022-12-19
 */
@Component
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    List<PmsProduct> selectProductStockByProductId(Integer id);
}
