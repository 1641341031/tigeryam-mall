package shop.tigeryam.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import shop.tigeryam.entity.CmsSubjectProductRelation;

import java.util.List;

/**
 * 商品和专题关系自定义Dao
 */
@Repository
public interface CmsSubjectProductRelationDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<CmsSubjectProductRelation> subjectProductRelationList);
}
