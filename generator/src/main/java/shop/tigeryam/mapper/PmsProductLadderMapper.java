package shop.tigeryam.mapper;

import org.springframework.stereotype.Repository;
import shop.tigeryam.entity.PmsProductLadder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 产品阶梯价格表(只针对同商品) Mapper 接口
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-28
 */
@Repository
public interface PmsProductLadderMapper extends BaseMapper<PmsProductLadder> {

}
