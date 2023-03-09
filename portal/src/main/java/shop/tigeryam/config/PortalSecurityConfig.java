package shop.tigeryam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import shop.tigeryam.component.KaptchaAuthenticationProvider;
import shop.tigeryam.service.IUmsUserService;

/**
 * security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PortalSecurityConfig{

    @Autowired
    private IUmsUserService userService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userService.loadUserByUsername(username);
    }

//    @Bean
//    public AuthenticationProvider kaptchaAuthenticationProvider(){
//        KaptchaAuthenticationProvider provider = new KaptchaAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService());
//        return provider;
//    }
}
