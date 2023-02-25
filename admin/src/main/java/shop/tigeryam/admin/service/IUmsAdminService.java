package shop.tigeryam.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import shop.tigeryam.entity.UmsAdmin;

public interface IUmsAdminService extends IService<UmsAdmin> {
    /**
     * 登录后获取token
     */
    String login(String username, String password);

    UserDetails loadUserByUsername(String username);
}
