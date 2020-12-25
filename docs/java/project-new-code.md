# 牛客网项目

> 当前项目基于spring mvc + mysql + Mybatis

## 项目初始化

### pom文件

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.nowcoder</groupId>
    <artifactId>community</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>community</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.4</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.3</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.alibaba/druid-spring-boot-starter -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.3</version>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

### 架构分层

- `config`	

> 存放作为配置类

- `controller`	

  > 该包下存放每个页面的控制器，控制器命名格式如下：`HomeController`，每一个控制器对应一个页面
  >
  > 将当前页面中需要用到的`service`进行注入
  >
  > ``` java
  > // 注入service
  > @Autowired
  > private DiscussPostService discussPostService;
  > ```
  >
  > 在控制器内部，处理当前页面的请求，通过声明注释，绑定不同的请求，执行对应的方法
  >
  > ``` java
  > @GetMapping("/index")
  > public String getIndexPage(Model model,Page page) {
  >     return "index"
  > }
  > ```
  >
  > 在返回页面前，通过调用对应的`service`的方法，获取需要的数据，通过Model传到页面中
  >
  > ``` java
  > 
  > // 调用service查询出所有的数据
  > List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
  > ```

- `dao` 		

  > 该包下存放各功能模块的Mapper接口，控制器命名格式如下：`UserMapper`
  >
  > 接口内部定义各种操作的行为方法，声明需要的参数以及返回值，这些定义的行为将在`service`层中去调用
  >
  > ``` java
  > @Mapper
  > public interface UserMapper {
  >     User selectById(int id);
  > }
  > 
  > ```

- `entity`		

  > 存放各种实体，一般与数据库字段对应，命名规范`User`

- `service`	

  > 存放各种服务类，命名规范`UserService`，通过注入`dao`层中对应的`Mapper`接口
  >
  > 在方法内部调用该接口的实现方法，获取数据，然后提供对应的方法给`Controller`控制器层使用
  >
  > ``` java
  > @Service
  > public class UserService {
  >     @Autowired
  >     private UserMapper userMapper;
  >     public User findUserById(int userId) {
  >         return userMapper.selectById(userId);
  >     }
  > }
  > ```


- `/resources/mapper`	

>当前文件夹用于存放 Mybatis 的xml配置文件，用于进行SQL操作，命名规范`user-mapper`
>
>``` xml
><?xml version="1.0" encoding="UTF-8" ?>
><!DOCTYPE mapper
>        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
>        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
><!-- 声明对某一个Mapper -->
><mapper namespace="com.nowcoder.community.dao.UserMapper">
>	<!-- 声明插入语句的常用字段名 -->
>    <sql id="insertFields">
>        username, password, salt, email, type, status, activation_code, header_url, create_time
>    </sql>
>	<!-- 声明查询语句的常用字段名 -->
>    <sql id="selectFields">
>        id, username, password, salt, email, type, status, activation_code, header_url, create_time
>    </sql>
>    
>    <select id="selectById" resultType="User">
>        select <include refid="selectFields"></include>
>        from user
>        where id = #{id}
>    </select>
></mapper>
>```
>
>



![](.\images\QQ截图20201222225357.jpg)

### 静态文件处理

> 将`css`、`images`、`js`，等静态文件放到`resources/static`目录下
>
> 将MVC页面模板文件，放置在`resources/templates`目录下

### 配置文件

``` properties
# ServerProperties
server.port=8080
server.servlet.context-path=/community

# ThymeleafProperties	模板引擎配置
spring.thymeleaf.cache=false

# DataSourceProperties  数据源配置
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/java_learning?characterEncoding=utf-8&useSSL=false&serverTimezone=Hongkong
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.hikari.maximum-pool-size=15
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=30000

# MybatisProperties
# mapper 映射路径
mybatis.mapper-locations=classpath:mapper/*.xml
# 实体的映射路径
mybatis.type-aliases-package=com.nowcoder.community.entity
# 是否自增id
mybatis.configuration.useGeneratedKeys=true
# 大驼峰与下划线互转
mybatis.configuration.mapUnderscoreToCamelCase=true

# logger
#logging.level.com.nowcoder.community=debug
#logging.file=d:/work/data/nowcoder/community.log

# MailProperties
spring.mail.host=smtp.sina.com
spring.mail.port=465
spring.mail.username=nowcoder@sina.com
spring.mail.password=nowcoder123
spring.mail.protocol=smtps
spring.mail.properties.mail.smtp.ssl.enable=true

# community
#community.path.domain=http://localhost:8080
#community.path.upload=d:/work/data/upload
```



## 首页

> 首页用于展示帖子列表，并完成分页查看功能

### 1.创建实体

在entity文件夹下创建`DiscussPost` 实体，定义属性，并设置`getter、setter`方法，重写`toString()`