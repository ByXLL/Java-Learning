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
>Spring Boot默认可以支持：`org.apache.tomcat.jdbc.pool.DataSource、`HikariDataSource`、`BasicDataSource`

## 自定义数据源类型

> 创建流程：
>
> 使用绑定了数据源属性的类 `DataSourceProperties`去初始化一个数据源构造者`initializeDataSourceBuilder`，然后调用`build`方法

```java
/**
 * Generic DataSource configuration.
 */
@ConditionalOnMissingBean(DataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type")
static class Generic {

   @Bean
   public DataSource dataSource(DataSourceProperties properties) {
       //使用DataSourceBuilder创建数据源，利用反射创建响应type的数据源，并且绑定相关属性
      return properties.initializeDataSourceBuilder().build();
   }

}
```

## 自动建表、插入数据

`DataSourceInitializer`、`ApplicationListener`

​	作用：

​		1）、`runSchemaScripts()`;运行建表语句；

​		2）、`runDataScripts()`;运行插入数据的sql语句；

默认只需要将文件命名为：

```properties
schema-*.sql # 建表sql
data-*.sql	# 数据sql
默认规则：schema.sql，schema-all.sql；
也可以在配置文件中使用   
	schema:
      - classpath:department.sql  # 指定路径
      ...
```

执行流程：

>在`DataSourceInitializer`中，有一个`createSchema`方法，通过`getScripts`去获取
>
>资源

``` java
boolean createSchema() {
    // 
    List<Resource> scripts = getScripts("spring.datasource.schema", this.properties.getSchema(), "schema");
    if (!scripts.isEmpty()) {
        if (!isEnabled()) {
            logger.debug("Initialization disabled (not running DDL scripts)");
            return false;
        }
        String username = this.properties.getSchemaUsername();
        String password = this.properties.getSchemaPassword();
        runScripts(scripts, username, password);
    }
    return !scripts.isEmpty();
}

/**
	 * Initialize the schema if necessary.
	 * @see DataSourceProperties#getData()
	 */
void initSchema() {
    List<Resource> scripts = getScripts("spring.datasource.data", this.properties.getData(), "data");
    if (!scripts.isEmpty()) {
        if (!isEnabled()) {
            logger.debug("Initialization disabled (not running data scripts)");
            return;
        }
        String username = this.properties.getDataUsername();
        String password = this.properties.getDataPassword();
        runScripts(scripts, username, password);
    }
}


private List<Resource> getScripts(String propertyName, List<String> resources, String fallback) {
    // 获取资源 当资源存在的时候 直接返回资源
    if (resources != null) {
        return getResources(propertyName, resources, true);
    }
    // 当资源不存在，就会去类路径下找 schema.sql，schema-all.sql 这两个文件
    String platform = this.properties.getPlatform();
    List<String> fallbackResources = new ArrayList<>();
    // platform 默认就是all
    fallbackResources.add("classpath*:" + fallback + "-" + platform + ".sql");
    fallbackResources.add("classpath*:" + fallback + ".sql");
    return getResources(propertyName, fallbackResources, false);
}

private List<Resource> getResources(String propertyName, List<String> locations, boolean validate) {
    List<Resource> resources = new ArrayList<>();
    for (String location : locations) {
        for (Resource resource : doGetResources(location)) {
            if (resource.exists()) {
                resources.add(resource);
            }
            else if (validate) {
                throw new InvalidConfigurationPropertyValueException(propertyName, resource,
                                                                     "The specified resource does not exist.");
            }
        }
    }
    return resources;
}


```



## 操作数据库

> Spring Boot还自动配置了`JdbcTemplate`操作数据库

```java
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(JdbcOperations.class)
class JdbcTemplateConfiguration {

	@Bean
	@Primary
	JdbcTemplate jdbcTemplate(DataSource dataSource, JdbcProperties properties) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		JdbcProperties.Template template = properties.getTemplate();
		jdbcTemplate.setFetchSize(template.getFetchSize());
		jdbcTemplate.setMaxRows(template.getMaxRows());
		if (template.getQueryTimeout() != null) {
			jdbcTemplate.setQueryTimeout((int) template.getQueryTimeout().getSeconds());
		}
		return jdbcTemplate;
	}

}

```

 

**查询数据**

``` java
@Controller
public class HelloController {
    // 注入JDBC Template
    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/getDepartments")
    public Map<String,Object> map() {
        // 使用jdbc 运行sql
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from department");
        return list.get(0);
    }
}
```



## 整合Druid数据源

> 创建一个`config.DruidConfig` 配置类

``` java
// DruidConfig 配置类
@Configuration
public class DruidConfig {

    // 使用 ConfigurationProperties 绑定
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return  new DruidDataSource();
    }

    // 配置Druid的监控
    // 配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
		
        // 设置配置
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");//默认就是允许所有访问
        initParams.put("deny","192.168.15.21");

        bean.setInitParameters(initParams);
        return bean;
    }


    // 配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        // 配置允许的 请求
        initParams.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }
}

```





## 整合MyBatis

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.4</version>
</dependency>
```

步骤：

​	1、配置数据源相关属性（见上一节Druid）

​	2、给数据库建表

​	3、创建JavaBean，设置`get set`

![](images\QQ截图20201206002422.jpg)

​	4、设置操作数据库的mapper

```java
@Mapper  // 指定这是一个操作数据库的mapper
public interface DepartmentMapper {
    @Select("select * from department where id=#{id}")
    Department getDeptById(Integer id);

    @Delete("delete from department where id=${id}")
    int deleteDeptById(Integer id);

    // 配置 是否使用自动生成的组件  并告诉 id 属性是封装组件的 在插入完成后返回
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    int insertDept(Department department);

    @Update("update department set departmentName=#{department_name} where id=#{id}")
    int updateDept(Department department);

}
```

​	5、设置请求的控制器

```java
// 直接返回json数据的controller
@RestController
public class DeptController {

    // 注入 mapper
    @Autowired
    DepartmentMapper departmentMapper;

    // 查询
    @GetMapping("/getDepartment")
    public Department getDepartment(@PathParam("id") Integer id) {
        // 使用 departmentMapper 里面声明的方法
        return departmentMapper.getDeptById(id);
    }

    // 添加
    @GetMapping("/addDepartment")
    public Department addDepartment(Department department) {
        departmentMapper.insertDept(department);
        return department;
    }
}
```

`问题`：当数据库使用的是下划线`department_name`字段名，而在`bean`中使用的是`departmentName`的时候，查询数据库字段就会为`null`

![](images\QQ截图20201206003257.jpg)

我们可以去创建一个 `MyBatisConfig` MyBatis的控制类

自定义MyBatis的配置规则；给容器中添加一个`ConfigurationCustomizer`

```java
@org.springframework.context.annotation.Configuration
public class MyBatisConfig {
    // 开启mybatis中的驼峰
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        // new 一个内部类
        return new ConfigurationCustomizer(){
            // 重写接口方法
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}

```

或者直接在配置文件中设置

```yaml
mybatis:
  configuration:
    map-underscore-to-camel-case: true
```





