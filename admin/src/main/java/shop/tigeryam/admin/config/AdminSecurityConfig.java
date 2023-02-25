package shop.tigeryam.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import shop.tigeryam.admin.service.IUmsAdminService;

/**
 * security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminSecurityConfig {

    @Autowired
    private IUmsAdminService umsAdminServiceService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> umsAdminServiceService.loadUserByUsername(username);
    }
}
