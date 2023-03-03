package shop.tigeryam.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MybatisPlusGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://192.168.1.101:3306/tigeryam_mall", "root", "root")
                .globalConfig(builder -> {
                    builder.author("tigeryam") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("D://project//tigeryam-mall//generator//src//main//java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("shop.tigeryam") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://project//tigeryam-mall//generator//src//main//resources")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("ums_resource"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
