package shop.tigeryam.service.impl;

import shop.tigeryam.entity.PmsProductFullReduction;
import shop.tigeryam.mapper.PmsProductFullReductionMapper;
import shop.tigeryam.service.IPmsProductFullReductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品满减表(只针对同商品) 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-28
 */
@Service
public class PmsProductFullReductionServiceImpl extends ServiceImpl<PmsProductFullReductionMapper, PmsProductFullReduction> implements IPmsProductFullReductionService {

}
