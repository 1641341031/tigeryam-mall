package shop.tigeryam.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import shop.tigeryam.admin.dto.PmsProductAttributeCategoryItem;
import shop.tigeryam.entity.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-29
 */
public interface IPmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {

    /**
     * 分页查询属性分类
     */
    Page<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

    /**
     * 获取包含属性的属性分类
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();

    /**
     * 删除属性分类
     */
    int delete(Long id);

    /**
     * 创建属性分类
     */
    int create(String name);

    /**
     * 修改属性分类
     */
    int update(Long id, String name);
}
