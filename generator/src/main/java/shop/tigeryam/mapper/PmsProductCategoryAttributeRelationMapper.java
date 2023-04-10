package shop.tigeryam.mapper;

import org.springframework.stereotype.Repository;
import shop.tigeryam.entity.PmsProductCategoryAttributeRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） Mapper 接口
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-30
 */
@Repository
public interface PmsProductCategoryAttributeRelationMapper extends BaseMapper<PmsProductCategoryAttributeRelation> {

}
