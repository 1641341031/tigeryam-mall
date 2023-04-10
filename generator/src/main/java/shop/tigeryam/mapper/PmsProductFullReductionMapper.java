package shop.tigeryam.mapper;

import org.springframework.stereotype.Repository;
import shop.tigeryam.entity.PmsProductFullReduction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 产品满减表(只针对同商品) Mapper 接口
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-28
 */
@Repository
public interface PmsProductFullReductionMapper extends BaseMapper<PmsProductFullReduction> {

}
