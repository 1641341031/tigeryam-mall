package shop.tigeryam.admin.service;

import shop.tigeryam.entity.UmsMemberLevel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-29
 */
public interface IUmsMemberLevelService extends IService<UmsMemberLevel> {

    /**
     * 获取所有会员等级
     * @param defaultStatus 是否为默认会员
     */
    List<UmsMemberLevel> list(Integer defaultStatus);
}
