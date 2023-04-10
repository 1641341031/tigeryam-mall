package shop.tigeryam.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import shop.tigeryam.admin.dto.PmsProductAttributeParam;
import shop.tigeryam.admin.dto.ProductAttrInfo;
import shop.tigeryam.entity.PmsProductAttributeMall;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
public interface IPmsProductAttributeMallService extends IService<PmsProductAttributeMall> {
    /**
     * 根据分类分页获取商品属性
     * @param cid 分类id
     * @param type 0->规格；1->参数
     */
    Page<PmsProductAttributeMall> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 获取商品分类对应属性列表
     */
    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);

    /**
     * 批量删除商品属性
     */
    @Transactional
    int delete(List<Long> ids);

    /**
     * 添加商品属性
     */
    @Transactional
    int create(PmsProductAttributeParam pmsProductAttributeParam);

    /**
     * 获取单个商品属性信息
     */
    PmsProductAttributeMall getItem(Long id);

    /**
     * 修改商品属性
     */
    int update(Long id, PmsProductAttributeParam productAttributeParam);

}
