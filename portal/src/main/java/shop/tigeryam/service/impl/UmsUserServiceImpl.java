package shop.tigeryam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import shop.tigeryam.domain.PortalUserDetails;
import shop.tigeryam.dto.RegisterParam;
import shop.tigeryam.entity.UmsUser;
import shop.tigeryam.entity.UmsUserLoginLog;
import shop.tigeryam.mapper.UmsUserLoginLogMapper;
import shop.tigeryam.mapper.UmsUserMapper;
import shop.tigeryam.service.IUmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import shop.tigeryam.util.IMailService;
import shop.tigeryam.util.JwtTokenUtil;
import shop.tigeryam.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * <p>
 * 前台用户表 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2022-11-30
 */
@Service
@Slf4j
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {
    @Autowired
    private UmsUserMapper umsUserMapper;
    @Autowired
    private UmsUserLoginLogMapper loginLogMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private IMailService mailServiceImpl;
    private static final String CONST_VERIFY_CHAR = "abcdefghijklmnopqrstuvwxyz";

    public UmsUser getByUsername(String username){
        QueryWrapper<UmsUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        UmsUser umsUser = umsUserMapper.selectOne(userQueryWrapper);
        if(null != umsUser){
            return umsUser;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsUser user = getByUsername(username);
        if(user != null){
            return new PortalUserDetails(user);
        }
        throw  new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public int updatePassword(String newPassword, String mailAddress, String username) {
        UmsUser user = new UmsUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        UpdateWrapper<UmsUser> umsUserUpdateWrapper = new UpdateWrapper<>();
        umsUserUpdateWrapper.eq("email", mailAddress).eq("username", username);
        int update = umsUserMapper.update(user, umsUserUpdateWrapper);
        return update;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try{
            UserDetails userDetails = loadUserByUsername(username);
            if(! passwordEncoder.matches(password, userDetails.getPassword())){
                throw new BadCredentialsException("用户名或密码错误");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(username);
        }catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsUser admin = getByUsername(username);
        if (admin == null) return;
        UmsUserLoginLog loginLog = new UmsUserLoginLog();
        loginLog.setUserId(admin.getId());
        loginLog.setCreateTime(LocalDateTime.now());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        loginLogMapper.insert(loginLog);
        log.info("保存用户登录记录成功");
    }

    @Override
    public void sendMail(String mailAddress, HttpSession session, String sessionName, String subject) throws Exception{
        //随机生成验证6位字符
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int digital = random.nextInt(CONST_VERIFY_CHAR.length());
            char c = CONST_VERIFY_CHAR.charAt(digital);
            code.append(c);
        }
        //把字符存到session
        session.setAttribute(sessionName, new String(code));
        //把验证字符发到用户邮箱
        mailServiceImpl.sendregisterMail(mailAddress, subject, new String(code));

    }

    @Override
    public UmsUser loadUserByEmail(String mailAddress){
        QueryWrapper<UmsUser> umsUserQueryWrapper = new QueryWrapper<>();
        umsUserQueryWrapper.eq("email", mailAddress);
        UmsUser user = getOne(umsUserQueryWrapper);
        return user;
    }


    @Override
    public Integer register(RegisterParam registerParam) {
        UmsUser umsUser = new UmsUser();
        umsUser.setUsername(registerParam.getUsername());
        umsUser.setEmail(registerParam.getEmail());
        umsUser.setPassword(passwordEncoder.encode(registerParam.getPassword()));
        int result = umsUserMapper.insert(umsUser);
        return result;
    }

}
