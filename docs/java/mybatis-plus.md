# MyBatis-Plus 详细操作

## 快速入门

### 1、安装 MyBatis-Plus

> 根据官网文档 在pom文件中添加依赖

``` xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.0.5</version>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

### 2、配置数据源

``` properties
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/my_batis_plus?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=Aa123456
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.hikari.maximum-pool-size=15
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=30000
```

### 3、配置mapper扫描路径

> 在应用主入口文件 添加 注释，用于声明 MyBatis_Plus 扫描的 mapper 文件目录

![image-20210414212932205](.\images\image-20210414212932205.png)

### 4、创建实体和mapper文件

>在项目目录下 创建 `entity` 包 添加 `User` 实体类，并创建 `mapper` 包 新建一个`UserMapper` 接口类
>
>在`UserMapper`接口类中，继承`BaseMapper`，泛型参数就是对应的实体 `User`

``` java
/**
 * 用户实体
 * @author By-Lin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}


/**
 * 用户 mapper 接口
 * 在 mybatis_plus中 我们只需要在 mapper 中去继承 BaseMapper 即可
 * @author By-Lin
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 这个时候简单的增删改查方法 已经完成
     * 当然我们也可以在这里去根据自己的业务场景去编写自己的方法
     */
}


```

### 5、声明`mapper-locations`路径

``` properties
# 在 properties 文件中设置mapper-locations的路径
mybatis-plus.mapper-locations=classpath:/com/mybatisplus/demo/mapper/xml/*Mapper.xml
```

### 6、在`pom`文件中声明 `resources`

``` xml
<build>
    <resources>
        <resource>
            <!-- xml放在java目录下-->
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
        <!--指定资源的位置（xml放在resources下，可以不用指定）-->
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```



-------------------

## 配置日志

> 在当前项目中，执行的sql是不可见的，我们需要开启日志
>
> 在配置文件中 通过 `mybatis-plus.configuration.log-impl` 设置日志方式

```properties
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```



-----------------

## CRUD扩展

### Insert 插入

>在 MyBatis_Plus中，插入操作默认是会插入一个全局唯一的id，默认使用的是雪花id，是一个*Long*类型的id
>
>插入成功将影响行数进行返回，并且会默认将id进行回填

#### 主键生成策略

>对于主键的自增
>
>1、在实体类上使用 `@TableId(type = IdType.ID_WORKER)` 注解
>
>2、数据库设置自增

在MyBatis_Plus中对Id自增中，可设置的类型包含以下5种；

```java
public enum IdType {
    AUTO(0),	// 数据库开启自增
    NONE(1),	// 不设置主键
    INPUT(2),	// 手动输入
    ID_WORKER(3),	// 默认全局id (雪花id)
    UUID(4),		// uuid
    ID_WORKER_STR(5);	// 默认全局id (雪花id) 字符串类型
}


// 实体类配置
public class User {
    /**
     * TableId 设置id自增
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}

// 插入操作
@Test
void insertUser() {
    User user = new User();
    user.setName("狗哥");
    user.setAge(18);
    user.setEmail("163@163.com");

    int row = userMapper.insert(user);
    System.out.println(row);
    System.out.println(user);
}
```



### update 更新

>在更新操作中存在两个方法，
>
>`update`方法需要传入两个参数，(实体，条件构造器)；
>
>`updateById` 方法则会通过实体对象中的声明的主键id来更新，注意传入的参数是一个实体
>
>两个方法都会通过实体传入的字段进行动态sql的拼接。

![image-20210415093045012](.\images\image-20210415093045012.png)

``` java
@Test
void updateUser() {
    User user = new User();
    user.setId(1382328618582482946L);
    user.setAge(20);
    int row = userMapper.updateById(user);
    System.out.println(row);
    System.out.println(user);
}
```

![image-20210415094250664](.\images\image-20210415094250664.png)



### 自动填充

在真实开发中，我们经常需要在表中去设置 `create_time`、`update_time`、`gmt_cerate`、`gmt_modified`等字段用记录当前数据的添加和修改时间

面对这种需求，一般存在两种解决方案

**1、方式一：数据库级别**

> 在数据库对表的字段开启日期更新，不推荐使用这种方式

![image-20210415095848130](.\images\image-20210415095848130.png)

>在数据库级别去处理的时候，我们无需在实体中去手动设置时间字段的值，数据库可以根据当前时间去自动更新

![image-20210415100306334](.\images\image-20210415100306334.png)

![image-20210415100345950](.\images\image-20210415100345950.png)

![image-20210415100533187](.\images\image-20210415100533187.png)

**2、代码级别**

>在这种模式下，需要将数据库的时间字段的默认值已经自动更新选项去掉

1、在实体类的字段属性上添加注解 `@TableField(fill = FieldFill.INSERT)`

``` java
public class User {
    /**
     * TableId 设置id自增
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    /**
     * 通过 TableField 列属性去设置 FieldFill 填充策略
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 在插入和更新的时候设置自动填充
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
```

2、编写处理器

>在handler包下，新建一个类，去实现 `MetaObjectHandler` 接口，并将该类添加至容器中

``` java
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时的更新策略
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 参数： 需要修改的字段名，值，元数据
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    /**
     * 更新时的策略
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
```

插入操作

![image-20210415102724805](.\images\image-20210415102724805.png)

更新操作

![image-20210415102914675](.\images\image-20210415102914675.png)

----------------------

### 乐观锁

> 当要更新一条记录的时候，希望这条记录没有被别人更新，如果没有使用乐观锁，后面修改的数据就会去覆盖了之前的数据
>
> 乐观锁实现方式：
>
> - 取出记录时，获取当前version
> - 更新时，带上这个version
> - 执行更新时， set version = newVersion where version = oldVersion
> - 如果version不对，就更新失败
>
> 说明:
>
> - **支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime**
> - 整数类型下 `newVersion = oldVersion + 1`
> - `newVersion` 会回写到 `entity` 中
> - 仅支持 `updateById(id)` 与 `update(entity, wrapper)` 方法
> - **在 `update(entity, wrapper)` 方法下, `wrapper` 不能复用!!!**

``` sql
-- 乐观锁 先查询 获得当前行的version version = 1
-- 线程A
update user set age = 18, version = version +1 where id = 1 and version = 1;

-- 线程B
update user set age = 20, version = version +1 where id = 1 and version = 1;

-- 当线程B抢先在线程A之前执行，并提交了，这个时候线程A去执行，version 就不等于1了，就会更新失败
```

1、修改数据库表，添加`version`字段

2、在实体类字段上添加 `@Version`注解

``` java
/**
* 添加 乐观锁 注解
*/
@Version
private Integer version;
```

3、配置乐观锁组件

> 使用 `OptimisticLockerInterceptor` 拦截器，去自动化配置乐观锁组件

``` java
/**
 * MyBatisPlusConfig 配置类
 * 设置mapper扫描目录
 * 开启事务
 * @author By-Lin
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.mybatisplus.demo.mapper")
public class MyBatisPlusConfig {
    /**
     * 注册乐观锁组件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
```

![image-20210415112558970](.\images\image-20210415112558970.png)

### 查询

> 在使用 `selectByMap` 方法中，能实现动态sql 按条件去查询，但更推荐使用 Wrapper 田间构造器

``` java
@Test
void testSelectById() {
    User user = userMapper.selectById(1);
    System.out.println(user);
}

@Test
void testSelectByBatchId() {
    List<User> userList = userMapper.selectBatchIds(Arrays.asList(1,2,3));
    userList.forEach(System.out::println);
}

@Test
void testSelectByMap() {
    HashMap<String,Object> map = new HashMap<>();
    // 自定义查询条件
    map.put("name","狗哥");

    List<User> userList = userMapper.selectByMap(map);
    userList.forEach(System.out::println);
}
```

![image-20210415114041095](.\images\image-20210415114041095.png)

### 分页查询

> 在`config`包下，新建一个配置类，用于配置对 MyBatis_Plus 的配置
>
> 在配置类中，添加注册分页组件
>
> 在查询的时候 通过 `new Page` page对象，通过传入页面和每页条数，结合条件构造器查询

``` java
// 在配置类中设置
@Bean
public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
    paginationInterceptor.setOverflow(false);
    return paginationInterceptor;
}

// 在查询业务代码中添加
@Test
void testPage() {
    // 新建一个 page 对象 泛型传入的是对于的实体  参数是页面和页面大小
    Page<User> page = new Page<>(1,5);

    userMapper.selectPage(page,null);;

    List<User> records = page.getRecords();
    long total = page.getTotal();
    System.out.println(total);
    System.out.println(records);
}
```

### 删除

``` java
@Test
void testDelete() {
    int row = userMapper.deleteById(1000);
    System.out.println(row);
}

@Test
void testDeleteBatchId() {
    int row = userMapper.deleteBatchIds(Arrays.asList(11111, 122211));
    System.out.println(row);
}

@Test
void testDeleteByMap() {
    HashMap<String,Object> map = new HashMap<>();
    int row = userMapper.deleteByMap(map);
    System.out.println(row);
}
```

### 逻辑删除

> 逻辑删除在真实项目经常存在，对于这种情况，我们需要在数据库中添加一个字段`is_delete`
>
> 所谓的逻辑删除就是通过修改当前数据的一个字段标识，在查询的时候进行过滤
>
> 在添加了逻辑删除后，默认当前实体的查询在查询的时候就会默认过滤打了删除标记的数据

1、在实体字段添加注解

``` java
@TableLogic
private Integer isDelete;
```

2、添加逻辑删除组件

```java
// 在 MyBatisPlus 配置类中添加逻辑删除组件
public class MyBatisPlusConfig {
    /**
     * 逻辑删除组件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}

```

3、设置配置文件

``` properties
mybatis-plus.global-config.db-config.logic-delete-value=1	# 删除的值
mybatis-plus.global-config.db-config.logic-not-delete-value=0	# 未删除的值
```

![image-20210415144232788](.\images\image-20210415144232788.png)

![image-20210415144349603](.\images\image-20210415144349603.png)

--------------

## 条件构造器

> 使用条件构造器，可以在增删改查的时候，通过构造器生成对应的条件语句

``` java
/**
* 通过 构造器 按条件查询
*/
@Test
void selectAllByWrapper() {
    // 查询name不为空，并且邮箱不为空，年龄大于等于20的
    // new 一个 QueryWrapper 泛型为 实体
    // 根据筛选条件
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.isNotNull("name")
        .isNotNull("email")
        .between("age",20,30);
    userMapper.selectList(queryWrapper).forEach(System.out::println);
}

/**
* 模糊查询
*/
@Test
void selectLike() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("name","狗")
        .likeRight("email","163");
    List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
    list.forEach(System.out::println);
}
```

![image-20210415160110783](.\images\image-20210415160110783.png)

![image-20210415155421753](.\images\image-20210415155421753.png)

![image-20210415160904815](.\images\image-20210415160904815.png)

---------------

## 性能分析插件

> 在MyBatis_Plus中，提供了性能分析插件，用于监听sql，当执行时间超过规定时间，sql将自动报错
>
> 需要在配置文件中声明当前环境为 `dev`还是`test`

1、在MyBatis_Plus配置类中添加插件

```java
public class MyBatisPlusConfig {
    /**
     * SQL 执行效率插件
     * Profile 用于声明 在什么环境下启用
     */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 设置sql执行的最大时间
        performanceInterceptor.setMaxTime(100);
        // 格式化sql
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
```

![image-20210415151405337](.\images\image-20210415151405337.png)

## 代码生成器

> 开启代码生成器，可以为我们自动生成dao、entity、service、controller层的代码
>
> 在项目目录下，新建一个 `CodeAutoGenerator` 类，在主函数中 新建一个 `AutoGenerator`
>
> 通过`GlobalConfig`全局配置对象构建全局配置，设置自动生成的文件输出路径和全局id主键策略和Swagger配置等
>
> 通过`DataSourceConfig`数据源配置，`注意：`这里配置完数据源后，`application`中依然需要配置数据源信息
>
> 通过`PackageConfig`配置包的配置，设置 `各层的输出package名`，`service、controller`等
>
> 通过`StrategyConfig`配置策略配置，设置需要字段映射的数据库表、驼峰命名、逻辑删除、自动填充字段、乐观锁和 Lombok等
>
> 将设置应用到 ``CodeAutoGenerator` `类中，并进行构造
>
> `需要注意导的包`

``` java
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

```



自动生成代码效果图

![image-20210416212845119](.\images\image-20210416212845119.png)