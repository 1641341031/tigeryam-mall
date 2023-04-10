package shop.tigeryam.service.impl;

import shop.tigeryam.entity.PmsProductCategoryAttributeRelation;
import shop.tigeryam.mapper.PmsProductCategoryAttributeRelationMapper;
import shop.tigeryam.service.IPmsProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-30
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl extends ServiceImpl<PmsProductCategoryAttributeRelationMapper, PmsProductCategoryAttributeRelation> implements IPmsProductCategoryAttributeRelationService {

}
