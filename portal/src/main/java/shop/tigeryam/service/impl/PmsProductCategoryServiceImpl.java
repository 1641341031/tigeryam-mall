package shop.tigeryam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import shop.tigeryam.entity.PmsProductCategory;
import shop.tigeryam.mapper.PmsProductCategoryMapper;
import shop.tigeryam.service.IPmsProductCategoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2022-12-10
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements IPmsProductCategoryService {

    @Autowired
    private PmsProductCategoryMapper pmsProductCategoryMapper;

    @Override
    public List<PmsProductCategory> getFatherLevel() {
        QueryWrapper<PmsProductCategory> pmsProductCategoryQueryWrapper = new QueryWrapper<>();
        pmsProductCategoryQueryWrapper.eq("parent_id", 0)
                .eq("is_parent", 1).orderByAsc("sort");
        return pmsProductCategoryMapper.selectList(pmsProductCategoryQueryWrapper);
    }

    @Override
    public List<PmsProductCategory> getSubLevel(Integer parentId) {
        QueryWrapper<PmsProductCategory> pmsProductCategoryQueryWrapper = new QueryWrapper<>();
        pmsProductCategoryQueryWrapper.eq("parent_id", parentId);
        return pmsProductCategoryMapper.selectList(pmsProductCategoryQueryWrapper);
    }

    @Override
    public List<PmsProductCategory> getCategoryTree(Integer parentId) {
        QueryWrapper<PmsProductCategory> pmsProductCategoryQueryWrapper = new QueryWrapper<>();
        pmsProductCategoryQueryWrapper.eq("parent_id", parentId);
        List<PmsProductCategory> pmsProductCategories = pmsProductCategoryMapper.selectList(pmsProductCategoryQueryWrapper);

        List<PmsProductCategory> list = new ArrayList<>();
        if(pmsProductCategories != null && pmsProductCategories.size() != 0){
            for (int i = 0; i < pmsProductCategories.size(); i++) {
                PmsProductCategory pmsProductCategory = pmsProductCategories.get(i);
                list.add(pmsProductCategory);
                if(pmsProductCategory.getIsParent() == 1){
                    List<PmsProductCategory> categoryTree = getCategoryTree(pmsProductCategory.getId());
                    pmsProductCategory.setChildren(categoryTree);
                }
            }
        }
        return list;
    }

}
