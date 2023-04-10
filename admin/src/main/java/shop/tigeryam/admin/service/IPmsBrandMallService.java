package shop.tigeryam.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import shop.tigeryam.admin.dto.PmsBrandParam;
import shop.tigeryam.entity.PmsBrandMall;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
public interface IPmsBrandMallService extends IService<PmsBrandMall> {
    /**
     * 修改品牌
     */
    @Transactional
    int updateBrand(Long id, PmsBrandParam pmsBrandParam);

    /**
     * 获取品牌详情
     */
    PmsBrandMall getBrand(Long id);
    /**
     * 创建品牌
     */
    int createBrand(PmsBrandParam pmsBrandParam);

    /**
     * 删除品牌
     */
    int deleteBrand(Long id);

    /**
     * 修改厂家制造商状态
     */
    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);

    /**
     * 修改显示状态
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 分页查询品牌
     */
    Page<PmsBrandMall> listBrand(String keyword, Integer showStatus, int pageNum, int pageSize);
}
