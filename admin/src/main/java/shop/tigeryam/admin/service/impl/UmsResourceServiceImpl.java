package shop.tigeryam.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import shop.tigeryam.entity.UmsResource;
import shop.tigeryam.mapper.UmsResourceMapper;
import shop.tigeryam.admin.service.IUmsResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-01
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements IUmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectList(new QueryWrapper<UmsResource>());
    }

    @Override
    public Page<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        Page<UmsResource> page = new Page<>(pageNum, pageSize);
        QueryWrapper<UmsResource> queryWrapper = new QueryWrapper<>();
        if(categoryId!=null){
            queryWrapper.eq("category_id", categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            queryWrapper.like("name", nameKeyword);
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            queryWrapper.like("url", urlKeyword);
        }
        return resourceMapper.selectPage(page, queryWrapper);
    }

    @Override
    public int delete(Long id) {
        int count = resourceMapper.deleteById(id);
        return count;
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        int count = resourceMapper.updateById(umsResource);
//        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(LocalDateTime.now());
        return resourceMapper.insert(umsResource);
    }

}
