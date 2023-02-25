package shop.tigeryam.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.tigeryam.admin.domain.AdminUserDetails;
import shop.tigeryam.admin.service.IUmsAdminService;
import shop.tigeryam.entity.UmsAdmin;
import shop.tigeryam.mapper.UmsAdminMapper;
import shop.tigeryam.util.JwtTokenUtil;

@Slf4j
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private  UmsAdminMapper umsAdminMapper;

    public UmsAdmin getByUsername(String username){
        QueryWrapper<UmsAdmin> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        UmsAdmin umsAdmin = umsAdminMapper.selectOne(userQueryWrapper);
        if(null != umsAdmin){
            return umsAdmin;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin user = getByUsername(username);
        if(user != null){
            return new AdminUserDetails(user);
        }
        throw  new UsernameNotFoundException("用户名或密码错误");
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
//            insertLoginLog(username);
        }catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }
}
