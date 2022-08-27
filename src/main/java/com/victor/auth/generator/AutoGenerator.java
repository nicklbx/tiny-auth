package com.victor.auth.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig.Builder;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Property;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * AuthGenerator
 * 代码自动生成
 *
 * @Author victor
 * @Date 2022/8/26 22:39
 */
public class AutoGenerator {

    private static final String URL = "jdbc:mysql://localhost:3306/victor_auth?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";


    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USER_NAME, PASSWORD)
                //全局配置
                .globalConfig(builder -> {
                    builder.author("victor") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride()
                            .disableOpenDir() // 结果生成后不打开输出目录
                            .outputDir("D:\\idea-workspace\\mall-all\\tiny-auth\\src\\main\\java"); // 指定输出目录
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.victor.auth") // 设置父包名
//                            .moduleName("") // 设置模块名
                            .entity("dao.entity") //设置entity输出包名
                            .controller("controller.v1") //设置controller输出包名
                            .service("service") //设置service输出包名
                            .serviceImpl("service.impl") //设置service impl输出包名
                            .mapper("dao.mapper") //设置mapper输出包名
                            .xml("mapper.xml") //设置Mapper XML 包名
                            .other("other")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    "src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                //策略配置
                .strategyConfig(builder -> {
                    //生成表名
                    List<String> tableList = new ArrayList<>();
                    tableList.add("pms_product");
                    tableList.add("sys_user");
                    tableList.add("sys_role");
                    tableList.add("sys_resource");
                    tableList.add("sys_user_role_relation");
                    tableList.add("sys_role_resource_relation");
                    //策略配置
                    builder.addInclude(tableList) // 设置需要生成的表名
                            .addTablePrefix("pms_", "sys_"); // 设置过滤表前缀
                    //entity生成配置
                    builder.entityBuilder()
                            .fileOverride() //覆盖entity文件
                            .addTableFills(new Property("createdAt", FieldFill.INSERT)) //insert时填充created_at
                            .addTableFills(
                                    new Property("updatedAt", FieldFill.INSERT_UPDATE)) //insert update时填充updated_at
                            .addTableFills(new Property("isDelete", FieldFill.INSERT)) //insert时填充is_delete为初始值
//                            .addTableFills(new Column("created_at", FieldFill.INSERT)) //new Column(下划线字段名)
//                            .addTableFills(new Column("updated_at", FieldFill.INSERT_UPDATE))
                            .idType(IdType.AUTO) //id生成规则
                            .logicDeleteColumnName("is_delete") //逻辑删除字段，需要在application.yml配置逻辑删除值
                            .enableLombok(); //启用lombok
                    //service生成配置
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService"); //默认生成的service接口前缀为I
                    //controller生成配置
                    builder.controllerBuilder()
                            .enableRestStyle();//生成restful controller
                })
                //模板引擎
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                //自定义模板
                .templateConfig(new Consumer<Builder>() { //自定义模板，默认模板在mybatis-plus-generator-3.5.2.jar包的templates下
                    @Override
                    public void accept(Builder builder) {
                        builder.entity("/templates/entity.java");//需要去掉模板引擎后缀.ftl
                    }
                })
                .execute();

    }

}
