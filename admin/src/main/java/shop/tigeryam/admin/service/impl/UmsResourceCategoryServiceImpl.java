package shop.tigeryam.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import shop.tigeryam.admin.service.IUmsResourceCategoryService;
import shop.tigeryam.entity.UmsResourceCategory;
import shop.tigeryam.mapper.UmsResourceCategoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 资源分类表 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-24
 */
@Service
public class UmsResourceCategoryServiceImpl extends ServiceImpl<UmsResourceCategoryMapper, UmsResourceCategory> implements IUmsResourceCategoryService {
    @Autowired
    private UmsResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<UmsResourceCategory> listAll() {
        return resourceCategoryMapper.selectList(new QueryWrapper<UmsResourceCategory>().orderByDesc("sort"));
    }

    @Override
    public int delete(Long id) {
        return resourceCategoryMapper.deleteById(id);
    }

    @Override
    public int update(Long id, UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setId(id);
        return resourceCategoryMapper.updateById(umsResourceCategory);
    }

    @Override
    public int create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(LocalDateTime.now());
        return resourceCategoryMapper.insert(umsResourceCategory);
    }
}
