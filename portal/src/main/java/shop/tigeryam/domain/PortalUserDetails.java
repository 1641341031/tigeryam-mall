package shop.tigeryam.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import shop.tigeryam.entity.UmsUser;

import java.util.Arrays;
import java.util.Collection;

/**
 * 用户详情封装
 */
public class PortalUserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final UmsUser umsUser;

    public PortalUserDetails(UmsUser umsUser) {
        this.umsUser = umsUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return Arrays.asList(new SimpleGrantedAuthority("TEST"));
    }

    @Override
    public String getPassword() {
        return umsUser.getPassword();
    }

    @Override
    public String getUsername() {
        return umsUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsUser.getStatus();
    }

    public UmsUser getUmsUser(){
        return umsUser;
    }
}
