package shop.tigeryam.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import shop.tigeryam.admin.dao.UmsAdminRoleRelationDao;
import shop.tigeryam.admin.domain.AdminUserDetails;
import shop.tigeryam.admin.dto.UmsAdminParam;
import shop.tigeryam.admin.service.IUmsAdminService;
import shop.tigeryam.entity.UmsAdmin;
import shop.tigeryam.entity.UmsAdminRoleRelation;
import shop.tigeryam.entity.UmsRole;
import shop.tigeryam.mapper.UmsAdminMapper;
import shop.tigeryam.mapper.UmsAdminRoleRelationMapper;
import shop.tigeryam.util.JwtTokenUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private  UmsAdminMapper umsAdminMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

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

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        QueryWrapper<UmsAdmin> umsAdminQueryWrapper = new QueryWrapper<>();
        umsAdminQueryWrapper.eq("username", username); // 要查看数据库里面的字段
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectList(umsAdminQueryWrapper);
        if (umsAdmins != null && umsAdmins.size() > 0){
            UmsAdmin admin = umsAdmins.get(0);
            return admin;
        }
        return null;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public Page<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<UmsAdmin> page = new Page<>(pageNum, pageSize);
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        if(!StrUtil.isEmpty(keyword)){
            queryWrapper.like("username", keyword).or().like("nickname", keyword);
        }
        Page<UmsAdmin> umsAdminPage = umsAdminMapper.selectPage(page, queryWrapper);
        return umsAdminPage;
    }

    @Override
    public int update(Long id, UmsAdmin admin) {
        admin.setId(id);
        UmsAdmin rawAdmin = umsAdminMapper.selectById(id);
        if(rawAdmin.getPassword().equals(admin.getPassword())){
            //与原加密密码相同的不需要修改
            admin.setPassword(null);
        }else {
            //与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(admin.getPassword())){
                admin.setPassword(null);
            }else {
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        int i = umsAdminMapper.updateById(admin);
        return i;
    }

    @Override
    public int delete(Long id) {
        int count = umsAdminMapper.deleteById(id);
        return count;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(LocalDateTime.now());
        umsAdmin.setUpdateTime(LocalDateTime.now());
        umsAdmin.setStatus(true);
        //查询是否有相同用户名的用户
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectList(queryWrapper);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }


    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        QueryWrapper<UmsAdminRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", adminId);
        adminRoleRelationMapper.delete(queryWrapper);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            for (int i = 0; i < list.size(); i++) {
                adminRoleRelationMapper.insert(list.get(i));
            }
        }
        return count;
    }

}
