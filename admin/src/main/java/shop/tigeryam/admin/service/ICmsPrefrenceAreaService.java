package shop.tigeryam.admin.service;

import shop.tigeryam.entity.CmsPrefrenceArea;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优选专区 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-30
 */
public interface ICmsPrefrenceAreaService extends IService<CmsPrefrenceArea> {
    /**
     * 获取所有优选专区
     */
    List<CmsPrefrenceArea> listAll();
}
