package com.bro.demo;

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

public class GeneratorApp {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("By_Lin");
        gc.setOpen(false);

        gc.setIdType(IdType.ASSIGN_ID);
        gc.setDateType(DateType.SQL_PACK);
        gc.setSwagger2(true);
        // 在Resources 下生成mapper.xml 并生成基础的resultMap
        gc.setBaseResultMap(true);
        gc.setFileOverride(true);
        // Mapper 接口名
        gc.setMapperName("%sMapper");
        // mapper.xml 文件名
        gc.setXmlName("%sMapper");
        // service 接口类 文件名
        gc.setServiceName("%sService");
        // service 接口实现类 文件名
        gc.setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/my_batis_plus?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Aa123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName();
        pc.setParent("com.bro.demo");
        mpg.setPackageInfo(pc);



        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude("goods");
        strategy.setTablePrefix(pc.getModuleName() + "_");
        // 设置逻辑删除 字段
        strategy.setLogicDeleteFieldName("is_del");
        // 设置自动填充  更新和删除时间
        TableFill ctTableFill = new TableFill("create_time", FieldFill.INSERT);
        TableFill upTableFill = new TableFill("update_time",FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(ctTableFill);
        tableFills.add(upTableFill);
        strategy.setTableFillList(tableFills);
        // 乐观锁
        strategy.setVersionFieldName("version");

        mpg.setStrategy(strategy);


        mpg.execute();
    }
}
