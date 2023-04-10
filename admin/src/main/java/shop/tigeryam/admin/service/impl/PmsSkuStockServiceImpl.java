package shop.tigeryam.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import shop.tigeryam.admin.dao.PmsSkuStockDao;
import shop.tigeryam.admin.service.IPmsSkuStockService;
import shop.tigeryam.entity.PmsSkuStock;
import shop.tigeryam.mapper.PmsSkuStockMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockMapper, PmsSkuStock> implements IPmsSkuStockService {

    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsSkuStockDao skuStockDao;

    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        QueryWrapper<PmsSkuStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", pid);
        if (!StrUtil.isEmpty(keyword)) {
            queryWrapper.like("sku_code", keyword );
        }
        return skuStockMapper.selectList(queryWrapper);
    }

    @Override
    public int update(Long pid, List<PmsSkuStock> skuStockList) {
        return skuStockDao.replaceList(skuStockList);
    }
}
