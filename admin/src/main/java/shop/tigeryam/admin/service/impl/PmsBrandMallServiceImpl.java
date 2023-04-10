package shop.tigeryam.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import shop.tigeryam.admin.dto.PmsBrandParam;
import shop.tigeryam.admin.service.IPmsBrandMallService;
import shop.tigeryam.entity.PmsBrandMall;
import shop.tigeryam.entity.PmsProductMall;
import shop.tigeryam.mapper.PmsBrandMallMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import shop.tigeryam.mapper.PmsProductMallMapper;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
@Service
public class PmsBrandMallServiceImpl extends ServiceImpl<PmsBrandMallMapper, PmsBrandMall> implements IPmsBrandMallService {

    @Autowired
    private PmsBrandMallMapper brandMapper;
    @Autowired
    private PmsProductMallMapper productMapper;

    @Override
    public int updateBrand(Long id, PmsBrandParam pmsBrandParam) {
        PmsBrandMall pmsBrand = new PmsBrandMall();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        //更新品牌时要更新商品中的品牌名称
        PmsProductMall product = new PmsProductMall();
        product.setBrandName(pmsBrand.getName());
        QueryWrapper<PmsProductMall> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.in("brand_id", id);
        productMapper.update(product, productQueryWrapper);
        return brandMapper.updateById(pmsBrand);
    }

    @Override
    public PmsBrandMall getBrand(Long id) {
        return brandMapper.selectById(id);
    }

    @Override
    public int createBrand(PmsBrandParam pmsBrandParam) {
        PmsBrandMall pmsBrand = new PmsBrandMall();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        return brandMapper.insert(pmsBrand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteById(id);
    }

//    @Override
//    public int deleteBrand(List<Long> ids) {
//        QueryWrapper<PmsBrandMall> queryWrapper = new QueryWrapper<>();
//        queryWrapper.in("id", ids);
//        return brandMapper.delete(queryWrapper);
//    }

    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        PmsBrandMall pmsBrand = new PmsBrandMall();
        pmsBrand.setFactoryStatus(factoryStatus);
        QueryWrapper<PmsBrandMall> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return brandMapper.update(pmsBrand, queryWrapper);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsBrandMall pmsBrand = new PmsBrandMall();
        pmsBrand.setShowStatus(showStatus);
        QueryWrapper<PmsBrandMall> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return brandMapper.update(pmsBrand, queryWrapper);
    }

    @Override
    public Page<PmsBrandMall> listBrand(String keyword, Integer showStatus, int pageNum, int pageSize) {
        Page<PmsBrandMall> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsBrandMall> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        if (!StrUtil.isEmpty(keyword)) {
            queryWrapper.like("name", keyword);
        }
        if(showStatus!=null){
            queryWrapper.eq("show_status", showStatus);
        }
        return brandMapper.selectPage(page, queryWrapper);
    }
}
