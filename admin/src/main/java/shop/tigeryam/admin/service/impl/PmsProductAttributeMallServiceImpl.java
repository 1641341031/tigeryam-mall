package shop.tigeryam.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import shop.tigeryam.admin.dao.PmsProductAttributeDao;
import shop.tigeryam.admin.dto.PmsProductAttributeParam;
import shop.tigeryam.admin.dto.ProductAttrInfo;
import shop.tigeryam.admin.service.IPmsProductAttributeMallService;
import shop.tigeryam.entity.PmsProductAttributeCategory;
import shop.tigeryam.entity.PmsProductAttributeMall;
import shop.tigeryam.mapper.PmsProductAttributeCategoryMapper;
import shop.tigeryam.mapper.PmsProductAttributeMallMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
@Service
public class PmsProductAttributeMallServiceImpl extends ServiceImpl<PmsProductAttributeMallMapper, PmsProductAttributeMall> implements IPmsProductAttributeMallService {
    @Autowired
    private PmsProductAttributeMallMapper productAttributeMapper;
    @Autowired
    private PmsProductAttributeDao productAttributeDao;
    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public Page<PmsProductAttributeMall> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        Page<PmsProductAttributeMall> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsProductAttributeMall> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort").eq("product_attribute_category_id", cid)
                .eq("type", type);
        return productAttributeMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return productAttributeDao.getProductAttrInfo(productCategoryId);
    }

    @Override
    public int delete(List<Long> ids) {
        //获取分类
        PmsProductAttributeMall pmsProductAttribute = productAttributeMapper.selectById(ids.get(0));
        Integer type = pmsProductAttribute.getType();
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectById(pmsProductAttribute.getProductAttributeCategoryId());
        QueryWrapper<PmsProductAttributeMall> productAttributeMallQueryWrapper = new QueryWrapper<>();
        productAttributeMallQueryWrapper.in("id", ids);
        int count = productAttributeMapper.delete(productAttributeMallQueryWrapper);
        //删除完成后修改数量
        if(type==0){
            if(pmsProductAttributeCategory.getAttributeCount()>=count){
                pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()-count);
            }else{
                pmsProductAttributeCategory.setAttributeCount(0);
            }
        }else if(type==1){
            if(pmsProductAttributeCategory.getParamCount()>=count){
                pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()-count);
            }else{
                pmsProductAttributeCategory.setParamCount(0);
            }
        }
        productAttributeCategoryMapper.updateById(pmsProductAttributeCategory);
        return count;
    }

    @Override
    public int create(PmsProductAttributeParam pmsProductAttributeParam) {
        PmsProductAttributeMall pmsProductAttribute = new PmsProductAttributeMall();
        BeanUtils.copyProperties(pmsProductAttributeParam, pmsProductAttribute);
        int count = productAttributeMapper.insert(pmsProductAttribute);
        //新增商品属性以后需要更新商品属性分类数量
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectById(pmsProductAttribute.getProductAttributeCategoryId());
        if(pmsProductAttribute.getType()==0){
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()+1);
        }else if(pmsProductAttribute.getType()==1){
            pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()+1);
        }
        productAttributeCategoryMapper.updateById(pmsProductAttributeCategory);
        return count;
    }

    @Override
    public PmsProductAttributeMall getItem(Long id) {
        return productAttributeMapper.selectById(id);
    }

    @Override
    public int update(Long id, PmsProductAttributeParam productAttributeParam) {
        PmsProductAttributeMall pmsProductAttribute = new PmsProductAttributeMall();
        pmsProductAttribute.setId(id);
        BeanUtils.copyProperties(productAttributeParam, pmsProductAttribute);
        return productAttributeMapper.updateById(pmsProductAttribute);
    }
}
