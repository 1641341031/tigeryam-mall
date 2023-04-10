package shop.tigeryam.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import shop.tigeryam.admin.dto.PmsProductCategoryParam;
import shop.tigeryam.admin.dto.PmsProductCategoryWithChildrenItem;
import shop.tigeryam.entity.PmsProductCategoryMall;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
public interface IPmsProductCategoryMallService extends IService<PmsProductCategoryMall> {
    /**
     * 修改商品分类
     */
    @Transactional
    int update(Long id, PmsProductCategoryParam pmsProductCategoryParam);
    /**
     * 创建商品分类
     */
    @Transactional
    int create(PmsProductCategoryParam pmsProductCategoryParam);

    /**
     * 分页获取商品分类
     */
    Page<PmsProductCategoryMall> getList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 根据ID获取商品分类
     */
    PmsProductCategoryMall getItem(Long id);

    /**
     * 以层级形式获取商品分类
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();

    /**
     * 删除商品分类
     */
    int delete(Long id);

    /**
     * 批量修改导航状态
     */
    int updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * 批量修改显示状态
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);
}
