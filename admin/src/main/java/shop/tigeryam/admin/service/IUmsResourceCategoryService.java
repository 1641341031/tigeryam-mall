package shop.tigeryam.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import shop.tigeryam.entity.UmsResourceCategory;

import java.util.List;

/**
 * <p>
 * 资源分类表 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-24
 */
public interface IUmsResourceCategoryService extends IService<UmsResourceCategory> {
    /**
     * 获取所有资源分类
     */
    List<UmsResourceCategory> listAll();

    /**
     * 删除资源分类
     */
    int delete(Long id);

    /**
     * 修改资源分类
     */
    int update(Long id, UmsResourceCategory umsResourceCategory);

    /**
     * 创建资源分类
     */
    int create(UmsResourceCategory umsResourceCategory);
}
