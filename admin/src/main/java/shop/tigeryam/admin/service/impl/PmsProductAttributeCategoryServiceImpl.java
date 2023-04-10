package shop.tigeryam.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import shop.tigeryam.admin.dao.PmsProductAttributeCategoryDao;
import shop.tigeryam.admin.dto.PmsProductAttributeCategoryItem;
import shop.tigeryam.admin.service.IPmsProductAttributeCategoryService;
import shop.tigeryam.entity.PmsProductAttributeCategory;
import shop.tigeryam.mapper.PmsProductAttributeCategoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-29
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements IPmsProductAttributeCategoryService {
    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Autowired
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;

    @Override
    public Page<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        Page<PmsProductAttributeCategory> attributeCategoryPage = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsProductAttributeCategory> queryWrapper = new QueryWrapper<>();
        return productAttributeCategoryMapper.selectPage(attributeCategoryPage, queryWrapper);
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return productAttributeCategoryDao.getListWithAttr();
    }

    @Override
    public int delete(Long id) {
        return productAttributeCategoryMapper.deleteById(id);
    }

    @Override
    public int create(String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        return productAttributeCategoryMapper.insert(productAttributeCategory);
    }

    @Override
    public int update(Long id, String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setId(id);
        return productAttributeCategoryMapper.updateById(productAttributeCategory);
    }


}
