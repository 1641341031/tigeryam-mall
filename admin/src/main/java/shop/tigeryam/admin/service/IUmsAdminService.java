package shop.tigeryam.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import shop.tigeryam.admin.dto.UmsAdminParam;
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

    /**
     * 根据用户名或昵称分页查询用户
     */
    Page<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, UmsAdmin admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);
}
