package shop.tigeryam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import shop.tigeryam.entity.PmsProductCategory;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tigeryam
 * @since 2022-12-10
 */
public interface IPmsProductCategoryService extends IService<PmsProductCategory> {

    /**
     * 获取分类首级
     * @return
     */
    List<PmsProductCategory> getFatherLevel();

    /**
     * 获取分类次级
     * @return
     */
    List<PmsProductCategory> getSubLevel(Integer parentId);

    List<PmsProductCategory> getCategoryTree(Integer parentId);

}
