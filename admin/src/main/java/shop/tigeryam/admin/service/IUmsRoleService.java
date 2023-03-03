package shop.tigeryam.admin.service;

import shop.tigeryam.entity.UmsMenu;
import shop.tigeryam.entity.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-02-28
 */
public interface IUmsRoleService extends IService<UmsRole> {

    /**
     * 根据管理员ID获取对应菜单
     */
    List<UmsMenu> getMenuList(Long adminId);
}
