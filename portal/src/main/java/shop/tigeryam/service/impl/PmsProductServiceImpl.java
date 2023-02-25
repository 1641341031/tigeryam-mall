package shop.tigeryam.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.tigeryam.entity.PmsProduct;
import shop.tigeryam.mapper.PmsProductMapper;
import shop.tigeryam.service.IPmsProductService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2022-12-15
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

    @Autowired
    private PmsProductMapper productMapper;
    @Override
    public List<PmsProduct> getProcutById(Integer id) {
        List<PmsProduct> productList = productMapper.selectProductStockByProductId(id);
        return productList;
    }
}
