package shop.tigeryam.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import shop.tigeryam.entity.UmsAdmin;
import shop.tigeryam.entity.UmsResource;
import shop.tigeryam.entity.UmsRole;

import java.util.List;

public interface IUmsAdminService extends IService<UmsAdmin> {
    /**
     * 登录后获取token
     */
    String login(String username, String password);

    UserDetails loadUserByUsername(String username);

    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取用户对应角色
     */
    List<UmsRole> getRoleList(Long adminId);
}
