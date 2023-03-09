package shop.tigeryam.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import shop.tigeryam.admin.service.IUmsAdminService;
import shop.tigeryam.admin.service.IUmsResourceService;
import shop.tigeryam.admin.service.impl.UmsResourceServiceImpl;
import shop.tigeryam.component.DynamicSecurityService;
import shop.tigeryam.entity.UmsResource;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminSecurityConfig {

    @Autowired
    private IUmsAdminService umsAdminServiceService;
    @Autowired
    private IUmsResourceService resourceService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> umsAdminServiceService.loadUserByUsername(username);
    }
/*
    public DynamicSecurityService dynamicSecurityService(){
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<UmsResource> resourceList = resourceService.listAll();
                for (UmsResource resource : resourceList) {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                }
                return map;
            }
        }
    }

 */
}
