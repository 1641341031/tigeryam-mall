package shop.tigeryam.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import shop.tigeryam.dto.RegisterParam;
import shop.tigeryam.entity.UmsUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前台用户表 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2022-11-30
 */
public interface IUmsUserService extends IService<UmsUser> {
    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 登录后获取token
     */
    String login(String username, String password);


    void sendMail(String mailAdress, HttpSession session, String sessionName, String subject) throws Exception;

    /**
     * 用户注册
     */
    @Transactional
    Integer register(RegisterParam registerParam);

    /**
     * 通过邮箱查找用户
     * @param mailAddress
     * @return
     */
    UmsUser loadUserByEmail(String mailAddress);

    /**
     * 用户忘记密码进行密码更新
     * @param newPassword
     * @param mailAddress
     * @return
     */
    int updatePassword(String newPassword, String mailAddress, String username);

}
