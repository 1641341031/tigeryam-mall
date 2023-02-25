package shop.tigeryam.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatisplus 相关配置
 */
@Configuration
@MapperScan("shop.tigeryam.mapper")
public class MybatisPlusConfig {
}
