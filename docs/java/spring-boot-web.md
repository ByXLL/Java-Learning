# Spring Boot Web

## 1、简介

使用SpringBoot；

1. 创建SpringBoot应用，选中我们需要的模块；
2. SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来
3. 自己编写业务代码；

**自动配置原理？**

这个场景SpringBoot帮我们配置了什么？能不能修改？能修改哪些配置？能不能扩展？

```bash
xxxxAutoConfiguration：帮我们给容器中自动配置组件；
xxxxProperties:配置类来封装配置文件的内容；

```

## 2、SpringBoot对静态资源的映射规则

### 设置和静态资源有关的参数，缓存时间等

```java
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties implements ResourceLoaderAware {
  //可以设置和静态资源有关的参数，缓存时间等
```

### 使用  webjars

```xml
<!--引入jquery-webjar-->在访问的时候只需要写webjars下面资源的名称即可
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.3.1</version>
</dependency>
```

> 所有`` /webjars/`` ，都去`classpath:/META-INF/resources/webjars/ `找资源，`webjars` 以jar包的方式引入静态资源；
>
> 导包地址 http://www.webjars.org/
>
> 然后就可以使用 `localhost:8080/webjars/jquery/3.3.1/jquery.js` 去访问该资源
>
> 

![](images/搜狗截图20180203181751.png)



### 默认访问路径

> `/**` 访问当前项目的任何资源，都去（静态资源的文件夹）找映射
>
> `localhost:8080/login.css  当访问这个非路由地址的时候 就会去下面默认定义的文件夹里面找 `login.css`

```java
"classpath:/META-INF/resources/", 
"classpath:/resources/",
"classpath:/static/", 
"classpath:/public/" 
"/"：当前项目的根路径
```

比如现在在 `resources/static/asserts` 文件夹下去导入了 css、js、images 等文件夹

去访问里面的资源可通过  `http://127.0.0.1:8080/asserts/css/bootstrap.min.css`  访问

### 默认首页 (欢迎页)

> 静态资源文件夹下的所有`index.html`页面；被`/**`映射
>
> `localhost:8080/`   找`index`页面

### 网站图标 

> 所有的 `**/favicon.ico`  都会在静态资源文件下找



```java
WebMvcAuotConfiguration：
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!this.resourceProperties.isAddMappings()) {
        logger.debug("Default resource handling disabled");
        return;
    }
    Integer cachePeriod = this.resourceProperties.getCachePeriod();
    if (!registry.hasMappingForPattern("/webjars/**")) {
        customizeResourceHandlerRegistration(
            registry.addResourceHandler("/webjars/**")
            .addResourceLocations(
                "classpath:/META-INF/resources/webjars/")
            .setCachePeriod(cachePeriod));
    }
    String staticPathPattern = this.mvcProperties.getStaticPathPattern();
    //静态资源文件夹映射
    if (!registry.hasMappingForPattern(staticPathPattern)) {
        customizeResourceHandlerRegistration(
            registry.addResourceHandler(staticPathPattern)
            .addResourceLocations(
                this.resourceProperties.getStaticLocations())
            .setCachePeriod(cachePeriod));
    }
}

//配置首页映射
@Bean
public WelcomePageHandlerMapping welcomePageHandlerMapping(
    ResourceProperties resourceProperties) {
    return new WelcomePageHandlerMapping(resourceProperties.getWelcomePage(),
                                         this.mvcProperties.getStaticPathPattern());
}

//配置喜欢的图标
@Configuration
@ConditionalOnProperty(value = "spring.mvc.favicon.enabled", matchIfMissing = true)
public static class FaviconConfiguration {

    private final ResourceProperties resourceProperties;

    public FaviconConfiguration(ResourceProperties resourceProperties) {
        this.resourceProperties = resourceProperties;
    }

    @Bean
    public SimpleUrlHandlerMapping faviconHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        //所有  **/favicon.ico 
        mapping.setUrlMap(Collections.singletonMap("**/favicon.ico",
                                                   faviconRequestHandler()));
        return mapping;
    }

    @Bean
    public ResourceHttpRequestHandler faviconRequestHandler() {
        ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
        requestHandler
            .setLocations(this.resourceProperties.getFaviconLocations());
        return requestHandler;
    }

}

```

----



## 3、模板引擎

> 常用模板引擎 `JSP、Velocity、Freemarker、Thymeleaf`
>
> SpringBoot推荐的`Thymeleaf`

模板引擎工作原理如下图：

![](images/template-engine.png)



### 引入Thymeleaf

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
切换thymeleaf版本
<properties>
    <thymeleaf-spring5.version>3.0.9.RELEASE</thymeleaf-spring5.version>
    <!-- 布局功能的支持程序  thymeleaf3主程序  layout2以上版本 -->
    <!-- thymeleaf2   layout1-->
    <thymeleaf-layout-dialect.version>2.2.2</thymeleaf-layout-dialect.version>
</properties>
```

### Thymeleaf使用

> 由下面代码我们可知，只要我们把HTML页面放在`classpath:/templates/`下，`Thymeleaf`就能自动渲染；

`Thymeleaf` 模板引擎核心代码：

```java
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

	private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");

	private static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/html");

	public static final String DEFAULT_PREFIX = "classpath:/templates/";

	public static final String DEFAULT_SUFFIX = ".html";
```

### 语法规则

> `th:text` 改变当前元素里面的文本内容
>
> `th` 任意html属性，来替换原生属性的值

![](images/2018-02-04_123955.png)



表达式

```properties
Simple expressions:（表达式语法）
    Variable Expressions: ${...}：获取变量值；OGNL；
    		1）、获取对象的属性、调用方法
    		2）、使用内置的基本对象：
    			#ctx : the context object.
    			#vars: the context variables.
                #locale : the context locale.
                #request : (only in Web Contexts) the HttpServletRequest object.
                #response : (only in Web Contexts) the HttpServletResponse object.
                #session : (only in Web Contexts) the HttpSession object.
                #servletContext : (only in Web Contexts) the ServletContext object.
                
                ${session.foo}
            3）、内置的一些工具对象：
#execInfo : information about the template being processed.
#messages : methods for obtaining externalized messages inside variables expressions, in the same way as they would be obtained using #{…} syntax.
#uris : methods for escaping parts of URLs/URIs
#conversions : methods for executing the configured conversion service (if any).
#dates : methods for java.util.Date objects: formatting, component extraction, etc.
#calendars : analogous to #dates , but for java.util.Calendar objects.
#numbers : methods for formatting numeric objects.
#strings : methods for String objects: contains, startsWith, prepending/appending, etc.
#objects : methods for objects in general.
#bools : methods for boolean evaluation.
#arrays : methods for arrays.
#lists : methods for lists.
#sets : methods for sets.
#maps : methods for maps.
#aggregates : methods for creating aggregates on arrays or collections.
#ids : methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).

    Selection Variable Expressions: *{...}：选择表达式：和${}在功能上是一样；
    	补充：配合 th:object="${session.user}：
   <div th:object="${session.user}">
    <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
    <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
    <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
    </div>
    
    Message Expressions: #{...}：获取国际化内容
    Link URL Expressions: @{...}：定义URL；
    		@{/order/process(execId=${execId},execType='FAST')}
    Fragment Expressions: ~{...}：片段引用表达式
    		<div th:insert="~{commons :: main}">...</div>
    		
Literals（字面量）
      Text literals: 'one text' , 'Another one!' ,…
      Number literals: 0 , 34 , 3.0 , 12.3 ,…
      Boolean literals: true , false
      Null literal: null
      Literal tokens: one , sometext , main ,…
Text operations:（文本操作）
    String concatenation: +
    Literal substitutions: |The name is ${name}|
Arithmetic operations:（数学运算）
    Binary operators: + , - , * , / , %
    Minus sign (unary operator): -
Boolean operations:（布尔运算）
    Binary operators: and , or
    Boolean negation (unary operator): ! , not
Comparisons and equality:（比较运算）
    Comparators: > , < , >= , <= ( gt , lt , ge , le )
    Equality operators: == , != ( eq , ne )
Conditional operators:条件运算（三元运算符）
    If-then: (if) ? (then)
    If-then-else: (if) ? (then) : (else)
    Default: (value) ?: (defaultvalue)
Special tokens:
    No-Operation: _ 
```

### 公共页面元素抽取

```html
1、抽取公共片段
<div th:fragment="copy">
&copy; 2011 The Good Thymes Virtual Grocery
</div>

2、引入公共片段
<div th:insert="footer :: copy"></div>
~{templatename::selector}：模板名::选择器
~{templatename::fragmentname}:模板名::片段名

3、默认效果：
insert的公共片段在div标签中
如果使用th:insert等属性进行引入，可以不用写~{}：
行内写法可以加上：[[~{}]];[(~{})]；
```



> 三种引入公共片段的th属性：
>
> `th:insert`：将公共片段整个插入到声明引入的元素中
>
> `th:replace`：将声明引入的元素替换为公共片段
>
> `h:include`：将被引入的片段的内容包含进这个标签中



## 4、SpringMVC自动配置

https://docs.spring.io/spring-boot/docs/1.5.10.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications

###  Spring MVC auto-configuration

> Spring Boot 自动配置好了SpringMVC
>
> 以下是SpringBoot对SpringMVC的默认配置  `WebMvcAutoConfiguration`
>
> - Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.
>
>   - 自动配置了`ViewResolver`（视图解析器：根据方法的返回值得到视图对象（View），视图对象决定如何渲染（转发？重定向？））
>
>   - `ContentNegotiatingViewResolver`：组合所有的视图解析器的；
>
>   - **如何定制**：我们可以自己给容器中添加一个视图解析器；自动的将其组合进来
>
>     ``` java
>     @SpringBootApplication
>     public class SpringBootWebDemoApplication {
>     
>         public static void main(String[] args) {
>             SpringApplication.run(SpringBootWebDemoApplication.class, args);
>         }
>     
>         @Bean
>         public ViewResolver myViewResolver() {
>             return new MyViewResolver();
>         }
>     
>         public static class MyViewResolver implements ViewResolver {
>     
>             @Override
>             public View resolveViewName(String viewName, Locale locale) throws Exception {
>                 return null;
>             }
>         }
>     }
>     
>     ```
>
>     
>
> - Support for serving static resources, including support for WebJars (see below).静态资源文件夹路径,webjars
>
> - Static `index.html` support. 静态首页访问
>
> - Custom `Favicon` support (see below).  favicon.ico
>
> - 自动注册了 of `Converter`, `GenericConverter`, `Formatter` beans.
>
>   - `Converter`：转换器； ` public String hello(User user)`：类型转换使用`Converter`
>
>   - `Formatter`  格式化器；  2017.12.17===Date
>
>     自己添加的格式化器转换器，我们只需要放在容器中即可
>
>     ```java
>     @Bean
>     @ConditionalOnProperty(prefix = "spring.mvc", name = "date-format")//在文件中配置日期格式化的规则
>     public Formatter<Date> dateFormatter() {
>         return new DateFormatter(this.mvcProperties.getDateFormat());//日期格式化组件
>     }
>     ```
>

> - Support for `HttpMessageConverters` (see below).
>
>   - HttpMessageConverter：SpringMVC用来转换Http请求和响应的；User---Json；
>
>   - `HttpMessageConverters` 是从容器中确定；获取所有的HttpMessageConverter；
>
>     `给容器中添加HttpMessageConverter，只需要将自己的组件注册容器中（@Bean,@Component）`
>
> - Automatic registration of `MessageCodesResolver` (see below).定义错误代码生成规则
>
> - Automatic use of a `ConfigurableWebBindingInitializer` bean (see below).
>
>   `我们可以配置一个ConfigurableWebBindingInitializer来替换默认的；（添加到容器）`



**org.springframework.boot.autoconfigure.web：web的所有自动场景；**

If you want to keep Spring Boot MVC features, and you just want to add additional [MVC configuration](https://docs.spring.io/spring/docs/4.3.14.RELEASE/spring-framework-reference/htmlsingle#mvc) (interceptors, formatters, view controllers etc.) you can add your own `@Configuration` class of type `WebMvcConfigurerAdapter`, but **without** `@EnableWebMvc`. If you wish to provide custom instances of `RequestMappingHandlerMapping`, `RequestMappingHandlerAdapter` or `ExceptionHandlerExceptionResolver` you can declare a `WebMvcRegistrationsAdapter` instance providing such components.

If you want to take complete control of Spring MVC, you can add your own `@Configuration` annotated with `@EnableWebMvc`.

### 扩展SpringMVC

比如以下 使用xml配置的旧场景

```xml
<mvc:view-controller path="/hello" view-name="success"/>
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/hello"/>
        <bean></bean>
    </mvc:interceptor>
</mvc:interceptors>
```

> 在Spring Boot 中我们只需要 在项目下创建 一个配置类，如`config.MyMvcCofig`
>
> ![](images\QQ截图20201129085730.jpg)
>
> **编写一个配置类（`@Configuration`），实现`WebMvcConfigurer`；不能标注`@EnableWebMvc`**
>
> ```java
> //使用WebMvcConfigurer可以来扩展SpringMVC的功能
> @Configuration
> public class MyMvcCofig implements WebMvcConfigurer  {
>     @Override
>     public void addViewControllers(ViewControllerRegistry registry){
>         // 创建视图映射规则  默认映射的都会去 templates 下找
>         registry.addViewController("/").setViewName("login");
>     }
> 
> }
> ```
>
> **既保留了所有的自动配置，也能用我们扩展的配置**

原理：

​	1）、WebMvcAutoConfiguration是SpringMVC的自动配置类

​	2）、在做其他自动配置时会导入；`@Import(**EnableWebMvcConfiguration**.class)`

``` java
@SuppressWarnings("deprecation")
@Configuration(proxyBeanMethods = false)
@Import(EnableWebMvcConfiguration.class)
@EnableConfigurationProperties({WebMvcProperties.class,org.springframework.boot.autoconfigure.web.ResourceProperties.class, WebProperties.class })
	@Order(0)
public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer {}
```



```java
    @Configuration
	public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration {
      private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();

	 //从容器中获取所有的WebMvcConfigurer
      @Autowired(required = false)
      public void setConfigurers(List<WebMvcConfigurer> configurers) {
          if (!CollectionUtils.isEmpty(configurers)) {
              this.configurers.addWebMvcConfigurers(configurers);
            	//一个参考实现；将所有的WebMvcConfigurer相关配置都来一起调用；  
            	@Override
             // public void addViewControllers(ViewControllerRegistry registry) {
              //    for (WebMvcConfigurer delegate : this.delegates) {
               //       delegate.addViewControllers(registry);
               //   }
              }
          }
	}
```

​	3）、容器中所有的WebMvcConfigurer都会一起起作用；

​	4）、我们的配置类也会被调用；



### 全面接管SpringMVC

SpringBoot对SpringMVC的自动配置不需要了，所有都是我们自己配置；所有的SpringMVC的自动配置都失效了

**我们需要在配置类中添加@EnableWebMvc即可；**

```java
@EnableWebMvc
@Configuration
public class MyMvcCofig implements WebMvcConfigurer  {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 创建视图映射规则  默认映射的都会去 templates 下找
        registry.addViewController("/").setViewName("success");
    }
}
```

原理：

> 为什么`@EnableWebMvc`自动配置就失效了?
>
> 导入的WebMvcConfigurationSupport只是SpringMVC最基本的功能；
>
> `@EnableWebMvc`的核心，使用了`@Import(DelegatingWebMvcConfiguration.class)`
>
> 而在 `DelegatingWebMvcConfiguration`中 去继承了 `WebMvcConfigurationSupport`
>
> 所以在 `WebMvcAutoConfiguration` 中的  `@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)` 查找到了使用，就不会使用 `WebMvcAutoConfiguration`

```java
// @EnableWebMvc 中的代码
@Import(DelegatingWebMvcConfiguration.class)
public @interface EnableWebMvc {}

// DelegatingWebMvcConfiguration 中的代码
@Configuration
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {}


// WebMvcAutoConfiguration 中的代码
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class,
		WebMvcConfigurerAdapter.class })
//重点是这一句 ConditionalOnMissingBean 容器中没有这个组件的时候，这个自动配置类才生效
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class,
		ValidationAutoConfiguration.class })
public class WebMvcAutoConfiguration {}

```



## 5、如何修改SpringBoot的默认配置

模式：

1. SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的（`@Bean、@Component`）如果有就用用户配置的，如果没有，才自动配置；如果有些组件可以有多个（`ViewResolver`）	`将用户配置的和自己默认的组合起来`
2. 在SpringBoot中会有非常多的`xxxConfigurer`帮助我们进行扩展配置
3. 在SpringBoot中会有很多的`xxxCustomizer`帮助我们进行定制配置

------------





## 6、国际化

### 步骤

> 1.创建国际化配置文件，在`resources`文件夹下创建 `i18n` 文件夹，在文件夹下创建不同页面的配置文件 如：`login.properties`，`login.properties`是作为login页的默认配置项，我们可以对应的添加

![](images\QQ截图20201129112724.jpg)

> 2.编写国际化配置文件，抽取页面需要显示的国际化消息

![](images\QQ截图20201129112724.jpg)

> 3.在配置文件 `application.properties` 中启用国际化 `spring.messages.basename=i18n.login`

> 4.去页面获取国际化的值

```html
<!DOCTYPE html>
<html lang="en"  xmlns:th="http://www.thymeleaf.org">
			<h1 class="h3 mb-3 font-weight-normal" th:text="#{login.tip}">Please sign in</h1>
			<label class="sr-only" th:text="#{login.username}">Username</label>
			<input type="text" class="form-control" placeholder="Username" th:placeholder="#{login.username}" required="" autofocus="">
			<label class="sr-only" th:text="#{login.password}">Password</label>
			<input type="password" class="form-control" placeholder="Password" th:placeholder="#{login.password}" required="">
			<div class="checkbox mb-3">
				<label>
          		<input type="checkbox" value="remember-me"/> [[#{login.remember}]]
        </label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit" th:text="#{login.btn}">Sign in</button>
		</form>
	</body>
</html>
```

点击链接切换国际化

>在项目类路径下，新建一个 `compotent.MyLocalResolver` 的 class，去实现 `LocaleResolver`，并重写方法，然后去我们自己定义的`MyMvcConfig` 中 通过`LocaleResolver` 配置并向容器中注册我们 自己定义的方法
>
>`http://127.0.0.1:8080/?lang=en_US`

```java
// compotent.MyLocalResolver.class
public class MyLocalResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 去获取请求里面的 parameter 的关键字
        String lang = request.getParameter("lang");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(lang)) {
            // 字符串截取  重组成地理信息
            String[] split = lang.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}

// 在 MyMvcConfig 启用自定义的方法
@Bean
public LocaleResolver localeResolver(){
    return new MyLocaleResolver();
}
```



### 原理：

> 国际化Locale（区域信息对象）；LocaleResolver（获取区域信息对象）；

```java
@Bean
@ConditionalOnMissingBean
@ConditionalOnProperty(prefix = "spring.mvc", name = "locale")
public LocaleResolver localeResolver() {
			if (this.mvcProperties
					.getLocaleResolver() == WebMvcProperties.LocaleResolver.FIXED) {
				return new FixedLocaleResolver(this.mvcProperties.getLocale());
			}
			AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
			localeResolver.setDefaultLocale(this.mvcProperties.getLocale());
			return localeResolver;
		}
// 默认的就是根据请求头带来的区域信息获取Locale进行国际化
```

![](images\QQ截图20201129134017.jpg)



### 总结：

SpringBoot自动配置好了管理国际化资源文件的组件

```java
@ConfigurationProperties(prefix = "spring.messages")
public class MessageSourceAutoConfiguration {
    
    /**
	 * Comma-separated list of basenames (essentially a fully-qualified classpath
	 * location), each following the ResourceBundle convention with relaxed support for
	 * slash based locations. If it doesn't contain a package qualifier (such as
	 * "org.mypackage"), it will be resolved from the classpath root.
	 */
	private String basename = "messages";  
    //我们的配置文件可以直接放在类路径下叫messages.properties；
    
    @Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		if (StringUtils.hasText(this.basename)) {
            //设置国际化资源文件的基础名（去掉语言国家代码的）
			messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(
					StringUtils.trimAllWhitespace(this.basename)));
		}
		if (this.encoding != null) {
			messageSource.setDefaultEncoding(this.encoding.name());
		}
		messageSource.setFallbackToSystemLocale(this.fallbackToSystemLocale);
		messageSource.setCacheSeconds(this.cacheSeconds);
		messageSource.setAlwaysUseMessageFormat(this.alwaysUseMessageFormat);
		return messageSource;
	}
```





## 7、Demo

### 1、静态文件配置

> 将静态文件 css、js、images放到 static 文件夹下
>
> 将需要使用模板引擎的页面放到 template 文件夹下

### 2、设置 MvcConfig

> 在 类路径下，创建一个 `config` 文件夹 ，然后创建一个`MyMvcConfig.class`配置文件
>
> ``` java
> @Configuration
> public class MyMvcCofig implements WebMvcConfigurer  {
> // 对于视图的映射，我们可以在控制器中 设置 @RequestMapping("/hello") 并 return 模板名字，如:  return "hello";
> 
> // 也可以使用这种 重写 WebMvcConfigurer 中的addViewControllers 方法
> //    @Override
> //    public void addViewControllers(ViewControllerRegistry registry) {
> //        // 创建视图映射规则  默认映射的都会去 templates 下找
> ////        registry.addViewController("/").setViewName("success");
> //    }
> 
>     // 因为所有的 WebMvcConfigurer 组件会一起起作用  专门写一个方法 用于返回 WebMvcConfigurer
>     @Bean  // 将组件注册到容器中
>     public WebMvcConfigurer webMvcConfigurer() {
>         WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){
>             // ctrl + o
>             @Override
>             public void addViewControllers(ViewControllerRegistry registry) {
>                 registry.addViewController("/").setViewName("login");
>                 registry.addViewController("/login.html").setViewName("login");
>             }
>         };
>         return webMvcConfigurer;
>     }
> }
> 
> ```



### 3、登陆

开发期间模板引擎页面修改以后，要实时生效

#### 1、禁用模板引擎的缓存

```properties
# 禁用缓存
spring.thymeleaf.cache=false 
```

页面修改完成以后ctrl+f9：重新编译

#### 2、登陆错误消息的显示

```html
<p style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
```

#### 3、登录控制器设置

> 设置路由，并接收页面传递上来的用户名和密码，设置对应的提示，当登录成功后，设置`session`，并使用 `redirect` 重定向至`main.html`

```java
@Controller
public class LoginController {

//    @RequestMapping(value = "user/login",method = RequestMethod.POST) 可以简写
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("username") String password, Map<String,Object> map, HttpSession session) {

        if("admin".equals(username)  && "admin".equals(password)) {
            // 设置session
            session.setAttribute("loginUser",username);
            // 防止表单重复提交 使用重定向 到 main
            return "redirect:/main.html";
        }else {
            map.put("msg","用户名密码错误");
            return "login";
        }

    }
}
```



### 4、拦截器进行登陆检查

#### 1.定义一个拦截器

> 在 `compotent`  中去添加一个 `LoginHandlerInterceptor` 登录拦截器类，去实现 `HandlerInterceptor`，并重写方法
>
> 在重写的 `preHandle` 方法中获取登录成功设置的 `session`，通过session判断是否登录
>
> 未登录时，设置对应的提示信息，并通过转发器，转发至登录页，并将请求头和响应头转发出去，最后需要`return false`
>
> 当登录了直接`return true`

```  java

// 登录拦截器
public class LoginHandlerInterceptor implements HandlerInterceptor {

    // 目标方法执行前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取之前登录成功设置的session
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null) {
            // 设置提示信息
            request.setAttribute("msg","没有权限请先登录");
            // 通过转发器 返回登录页面  并将请求和响应 转发出去
            request.getRequestDispatcher("/").forward(request,response);
            // 未登录 不放行请求
            return false;
        }else {
            // 登录了 放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
```

#### 2.注册拦截器

>对`MyMvcCofig` mvc配置类中 设置的 `webMvcConfigurer`中重写 `addInterceptors` 方法，添加我们定义的登录拦截器
>
>并对设置的登录拦截器做请求白名单处理，`（拦截所有请求，但是放行默认首页请求和登录相关的请求）`
>
>`springBoot 对静态资源做了处理，不会影响静态资源的访问`

``` java
@Configuration
public class MyMvcCofig implements WebMvcConfigurer  {
    @Bean  // 将组件注册到容器中
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){
            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 添加我们定义的登录拦截器  拦截全部请求  并排除登录页的请求
                // springBoot 对静态资源做了处理，不会影响静态资源的访问
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index.html","/login","/user/login");
            }
        };
        return webMvcConfigurer;
    }

}

```



### 5、抽取页面公共模块

> 将页面中公共的模块进行 抽离

```html

<!--	th:fragment="navbar"  声明一个模块	-->
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0" th:fragment="navbar">
	<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">[[${session.loginUser}]]</a>
	<input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">Sign out</a>
        </li>
    </ul>
</nav>


<!--	引入抽取的 navbar	-->
<div th:replace="~{dashboard :: nanvbar}"></div>
```



### 6、CRUD-员工列表

> 首先创建对应的控制器 `EmployeeController`，声明为控制器
>
> 将`dao`层进行注入
>
> 创建方法 声明路由，并在方法内部，调用`dao`层的方法获取数据
>
> 将查询出来的数据放到请求域中，并返回页面模板



``` java
@Controller
public class EmployeeController {
    // 注入 dao
    @Autowired
    EmployeeDao employeeDao;

    // 查询所有员工列表
    @GetMapping("/getEmps")
    public String getEmps(Model model) {
        // 调用dao里面的 getAll方法  接受数据
        Collection<Employee> employees = employeeDao.getAll();

        // 将查询 出来的数据 放到请求域中
        model.addAttribute("employees",employees);

        return "/emp/list";
    }
}

```



