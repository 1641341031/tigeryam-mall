package shop.tigeryam.service.impl;

import shop.tigeryam.entity.PmsProductLadder;
import shop.tigeryam.mapper.PmsProductLadderMapper;
import shop.tigeryam.service.IPmsProductLadderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品阶梯价格表(只针对同商品) 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-28
 */
@Service
public class PmsProductLadderServiceImpl extends ServiceImpl<PmsProductLadderMapper, PmsProductLadder> implements IPmsProductLadderService {

}
