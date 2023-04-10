package shop.tigeryam.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import shop.tigeryam.entity.CmsPrefrenceAreaProductRelation;

import java.util.List;

/**
 * 优选和商品关系自定义Dao
 */
@Repository
public interface CmsPrefrenceAreaProductRelationDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList);
}
