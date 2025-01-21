package com.jingdianjichi.circle.infra.mybatis.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * @author jay
 * @since 2025/1/21 下午7:03
 */
public class MybatisPlusGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jc-club?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "Wyj199751";
        String author = "jay";
        String outputDir = "C:\\Users\\10854\\IdeaProjects\\jc-club\\jc-club-circle\\jc-club-circle-infra\\src\\main\\java";
        String parentPackage = "com.jingdianjichi.circle.infra.mybatis";
        List<String> tableNames = Arrays.asList("sensitive_words",
                "share_circle",
                "share_comment_reply",
                "share_message",
                "share_moment");
        // 使用 FastAutoGenerator 快速配置代码生成器
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .outputDir(outputDir); // 输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackage) // 设置父包名
                            .entity("model") // 设置实体类包名
                            .mapper("dao") // 设置 Mapper 接口包名
                            .service("service") // 设置 Service 接口包名
                            .serviceImpl("service.impl") // 设置 Service 实现类包名
                            .xml("mappers"); // 设置 Mapper XML 文件包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames) // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .controllerBuilder()
                            .enableRestStyle(); // 启用 REST 风格
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 模板引擎
                .execute(); // 执行生成
    }
}
