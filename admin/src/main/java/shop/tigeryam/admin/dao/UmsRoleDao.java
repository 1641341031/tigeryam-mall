package shop.tigeryam.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import shop.tigeryam.entity.UmsMenu;

import java.util.List;

/**
 * 根据后台角色管理自定义Dao
 */
@Component
public interface UmsRoleDao {
    /**
     * 根据后台用户ID获取菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);
}
