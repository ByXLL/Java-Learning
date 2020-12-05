# JDBC

`pom.xml` 

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

数据库连接配置`application.yml`

```yml
spring:
  datasource:
    username: java
    url: jdbc:mysql://127.0.0.1/java_learning
    password: Aa123456
    driver-class-name: com.mysql.jdbc.Driver
    initialization-mode: always
```

在测试模块中打印数据源

```java
@SpringBootTest
class SpringBootDemoDataJdbcApplicationTests {
    @Autowired
    DataSource dataSource;
    
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
```

> 在spring boot 2.4中使用的数据源是`com.zaxxer.hikari.HikariDataSource`
>
> 数据源的相关配置都在`DataSourceProperties`里面



## 自动装配原理

>在包`org.springframework.boot.autoconfigure.jdbc`中
>
>参考`DataSourceConfiguration`，根据配置创建数据源，默认使用Tomcat连接池，可以使用`spring.datasource.type`指定自定义的数据源类型
>
>SpringBoot默认可以支持；

