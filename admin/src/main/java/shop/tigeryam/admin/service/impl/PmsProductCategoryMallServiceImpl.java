package shop.tigeryam.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import shop.tigeryam.admin.dao.PmsProductCategoryAttributeRelationDao;
import shop.tigeryam.admin.dao.PmsProductCategoryDao;
import shop.tigeryam.admin.dto.PmsProductCategoryParam;
import shop.tigeryam.admin.dto.PmsProductCategoryWithChildrenItem;
import shop.tigeryam.admin.service.IPmsProductCategoryMallService;
import shop.tigeryam.entity.PmsProductCategoryAttributeRelation;
import shop.tigeryam.entity.PmsProductCategoryMall;
import shop.tigeryam.entity.PmsProductMall;
import shop.tigeryam.mapper.PmsProductCategoryAttributeRelationMapper;
import shop.tigeryam.mapper.PmsProductCategoryMallMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import shop.tigeryam.mapper.PmsProductMallMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
@Service
public class PmsProductCategoryMallServiceImpl extends ServiceImpl<PmsProductCategoryMallMapper, PmsProductCategoryMall> implements IPmsProductCategoryMallService {
    @Autowired
    private PmsProductCategoryMallMapper productCategoryMapper;
    @Autowired
    private PmsProductCategoryDao productCategoryDao;
    @Autowired
    private PmsProductCategoryAttributeRelationDao productCategoryAttributeRelationDao;
    @Autowired
    private PmsProductMallMapper productMapper;
    @Autowired
    private PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;

    @Override
    public int update(Long id, PmsProductCategoryParam pmsProductCategoryParam) {
        PmsProductCategoryMall productCategory = new PmsProductCategoryMall();
        productCategory.setId(id);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        setCategoryLevel(productCategory);
        //更新商品分类时要更新商品中的名称
        PmsProductMall product = new PmsProductMall();
        product.setProductCategoryName(productCategory.getName());
        QueryWrapper<PmsProductMall> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_category_id", id);
        productMapper.update(product, productQueryWrapper);
        //同时更新筛选属性的信息
        if(!CollectionUtils.isEmpty(pmsProductCategoryParam.getProductAttributeIdList())){
            QueryWrapper<PmsProductCategoryAttributeRelation> attributeRelationQueryWrapper = new QueryWrapper<>();
            attributeRelationQueryWrapper.eq("product_category_id",id);
            productCategoryAttributeRelationMapper.delete(attributeRelationQueryWrapper);
            insertRelationList(id,pmsProductCategoryParam.getProductAttributeIdList());
        }else{
            QueryWrapper<PmsProductCategoryAttributeRelation> attributeRelationQueryWrapper = new QueryWrapper<>();
            attributeRelationQueryWrapper.eq("product_category_id",id);
            productCategoryAttributeRelationMapper.delete(attributeRelationQueryWrapper);
        }
        return productCategoryMapper.updateById(productCategory);
    }

    @Override
    public int create(PmsProductCategoryParam pmsProductCategoryParam) {
        PmsProductCategoryMall productCategory = new PmsProductCategoryMall();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        //没有父分类时为一级分类
        setCategoryLevel(productCategory);
        int count = productCategoryMapper.insert(productCategory);
        //创建筛选属性关联
        List<Long> productAttributeIdList = pmsProductCategoryParam.getProductAttributeIdList();
        if(!CollectionUtils.isEmpty(productAttributeIdList)){
            insertRelationList(productCategory.getId(), productAttributeIdList);
        }
        return count;
    }

    @Override
    public int delete(Long id) {
        return productCategoryMapper.deleteById(id);
    }

    @Override
    public Page<PmsProductCategoryMall> getList(Long parentId, Integer pageSize, Integer pageNum) {
        Page<PmsProductCategoryMall> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsProductCategoryMall> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort").eq("parent_id", parentId);
        return productCategoryMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return productCategoryDao.listWithChildren();
    }

    /**
     * 根据分类的parentId设置分类的level
     */
    private void setCategoryLevel(PmsProductCategoryMall productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            PmsProductCategoryMall parentCategory = productCategoryMapper.selectById(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }

    /**
     * 批量插入商品分类与筛选属性关系表
     * @param productCategoryId 商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationDao.insertList(relationList);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsProductCategoryMall productCategory = new PmsProductCategoryMall();
        productCategory.setShowStatus(showStatus);
        QueryWrapper<PmsProductCategoryMall> categoryMallQueryWrapper = new QueryWrapper<>();
        categoryMallQueryWrapper.in("id", ids);
        return productCategoryMapper.update(productCategory, categoryMallQueryWrapper);
    }

    @Override
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        PmsProductCategoryMall productCategory = new PmsProductCategoryMall();
        productCategory.setNavStatus(navStatus);
        QueryWrapper<PmsProductCategoryMall> categoryMallQueryWrapper = new QueryWrapper<>();
        categoryMallQueryWrapper.in("id", ids);
        return productCategoryMapper.update(productCategory, categoryMallQueryWrapper);
    }

    @Override
    public PmsProductCategoryMall getItem(Long id) {
        return productCategoryMapper.selectById(id);
    }

}
