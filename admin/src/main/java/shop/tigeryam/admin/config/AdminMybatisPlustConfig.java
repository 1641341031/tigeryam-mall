package shop.tigeryam.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("shop.tigeryam.mapper")
public class AdminMybatisPlustConfig {
}
