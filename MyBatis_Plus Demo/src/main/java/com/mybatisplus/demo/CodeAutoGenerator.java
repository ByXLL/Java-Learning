package com.mybatisplus.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * 代码生成器类
 * @author By-Lin
 */
public class CodeAutoGenerator {
    public static void main(String[] args) {
        // 构建代码自动生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();
        // 配置策略
        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 设置文件输出路径
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("By-Lin");
        gc.setOpen(false);
        // 是否覆盖源文件
        gc.setFileOverride(false);
        // 去service 的 I 前缀
        gc.setServiceName("%sService");
        // 配置全局id策略 (雪花算法)
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.SQL_PACK);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        autoGenerator.setGlobalConfig(gc);

        // 2.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/my_batis_plus?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Aa123456");
        dsc.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dsc);

        // 3.包的配置
        PackageConfig pc = new PackageConfig();
        // 设置模块的名称
//        pc.setModuleName("demo");
        // 设置父路径
        pc.setParent("com.mybatisplus.demo");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        autoGenerator.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 自动映射的表集合
        strategy.setInclude("goods");
        // 设置 包命名 下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 设置 数据库列命名 下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 开启Lombok
        strategy.setEntityLombokModel(true);
        // 设置驼峰controller命名
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");

        // 设置逻辑删除 字段
        strategy.setLogicDeleteFieldName("is_del");
        // 设置自动填充  更新和删除时间
        TableFill ctTableFill = new TableFill("create_time",FieldFill.INSERT);
        TableFill upTableFill = new TableFill("update_time",FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(ctTableFill);
        tableFills.add(upTableFill);
        strategy.setTableFillList(tableFills);

        // 乐观锁
        strategy.setVersionFieldName("version");


        autoGenerator.setStrategy(strategy);

        // 执行构造
        autoGenerator.execute();
    }
}
