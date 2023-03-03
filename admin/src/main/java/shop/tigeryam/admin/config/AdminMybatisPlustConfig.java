package shop.tigeryam.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"shop.tigeryam.mapper", "shop.tigeryam.admin.dao"})
public class AdminMybatisPlustConfig {
}
