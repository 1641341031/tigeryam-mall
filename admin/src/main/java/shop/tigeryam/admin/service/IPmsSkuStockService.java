package shop.tigeryam.admin.service;

import shop.tigeryam.entity.PmsSkuStock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku的库存 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
public interface IPmsSkuStockService extends IService<PmsSkuStock> {

    /**
     * 根据产品id和skuCode关键字模糊搜索
     */
    List<PmsSkuStock> getList(Long pid, String keyword);

    /**
     * 批量更新商品库存信息
     */
    int update(Long pid, List<PmsSkuStock> skuStockList);
}
