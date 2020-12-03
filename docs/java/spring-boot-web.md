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

-----

## 7、错误处理

SpringBoot默认的错误处理机制

默认效果：

​		1、浏览器，返回一个默认的错误页面

![](images\搜狗截图20180226173408.png)

  浏览器发送请求的请求头：

![](images\搜狗截图20180226180347-1606896803340.png)

​		2）、如果是其他客户端，默认响应一个json数据

![](images\搜狗截图20180226173527-1606896828333.png)

​		![](images\搜狗截图20180226180504.png)

原理：

> 可以参照`ErrorMvcAutoConfiguration` 错误处理的自动配置



步骤：

>一旦系统出现400或者500之类的报错，`ErrorPageCustomizer`（定制错误的响应规则）就会生效，就会来到`/error`请求
>
>进入到`BasicErrorController`处理，根据请求头的`accept`类型去决定走的是响应页面还是响应Json。当去响应页面的时候，就会调用`ErrorViewResolver` 拿到所有的错误视图解析器得到`modelAndView`。
>
>```java
>protected ModelAndView resolveErrorView(HttpServletRequest request,
>      HttpServletResponse response, HttpStatus status, Map<String, Object> model) {
>    //所有的ErrorViewResolver得到ModelAndView
>   for (ErrorViewResolver resolver : this.errorViewResolvers) {
>      ModelAndView modelAndView = resolver.resolveErrorView(request, status, model);
>      if (modelAndView != null) {
>         return modelAndView;
>      }
>   }
>   return null;
>}
>```
>
>然后就会走到`DefaultErrorViewResolver`，默认错误视图解析器，对当前需要前往的页面进行解析配置，根据对应的状态码，拼接视图地址`error/404`
>
>然后判断模板引擎是否可用，可用则返回到`errorViewName`指定的视图地址，不可用就在静态资源文件夹下找`errorViewName`对应的页面，没有就会使用 SpringBoot默认的
>
>在获取到页面后，通过`getErrorAttributes`获取错误信息， 向页面中去注入错误信息



### ErrorPageCustomizer

``` java
@Value("${error.path:/error}")
private String path = "/error";  // 系统出现错误以后来到error请求进行处理；（web.xml注册的错误页面规则）
```



### BasicErrorController

> 这个具体使用 `text/html`或者 `json` ,由请求头的 `Request HEaders accept`决定

```java
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BasicErrorController extends AbstractErrorController {
    
    // 当触发了 error 请求，就会来到默认错误控制器 产生 页面或者json 两种不同的情况去做处理
    @RequestMapping(produces = "text/html")//产生html类型的数据；浏览器发送的请求来到这个方法处理
	public ModelAndView errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
				request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
        
        //去哪个页面作为错误页面；包含页面地址和页面内容
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
	}

	@RequestMapping
	@ResponseBody    //产生json数据，其他客户端来到这个方法处理；
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request,
				isIncludeStackTrace(request, MediaType.ALL));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Map<String, Object>>(body, status);
	}
}
```



### DefaultErrorViewResolver

```java
@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status,
			Map<String, Object> model) {
		ModelAndView modelAndView = resolve(String.valueOf(status), model);
		if (modelAndView == null && SERIES_VIEWS.containsKey(status.series())) {
			modelAndView = resolve(SERIES_VIEWS.get(status.series()), model);
		}
		return modelAndView;
	}

	private ModelAndView resolve(String viewName, Map<String, Object> model) {
        //默认SpringBoot可以去找到一个页面？  error/404
		String errorViewName = "error/" + viewName;
        
        //模板引擎可以解析这个页面地址就用模板引擎解析
		TemplateAvailabilityProvider provider = this.templateAvailabilityProviders
				.getProvider(errorViewName, this.applicationContext);
		if (provider != null) {
            //模板引擎可用的情况下返回到errorViewName指定的视图地址
			return new ModelAndView(errorViewName, model);
		}
        //模板引擎不可用，就在静态资源文件夹下找errorViewName对应的页面   error/404.html
		return resolveResource(errorViewName, model);
	}
```







### DefaultErrorAttributes

```java
@Override
public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,boolean includeStackTrace) {
    Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
    // 时间戳
    errorAttributes.put("timestamp", new Date());
    // 错误状态码
    addStatus(errorAttributes, requestAttributes);
    // 异常对象
    addErrorDetails(errorAttributes, requestAttributes, includeStackTrace);
    addPath(errorAttributes, requestAttributes);
    return errorAttributes;
}
```













### 定制错误响应

#### 	**1、如何定制错误的页面**

​			**1）、有模板引擎的情况下： error/状态码;** 【将错误页面命名为  错误状态码.html 放在模板引擎文件夹里面的 error文件夹下】，发生此状态码的错误就会来到  对应的页面

​			我们可以使用4xx和5xx作为错误页面的文件名来匹配这种类型的所有错误，优先寻找精确的状态码（404.html）

> 页面能获取的信息：
>
> - timestamp：时间戳
>
> - status：状态码
>
> - error：错误提示
>
>
> - exception：异常对象
>
>
> - message：异常消息
>
>
> - errors：JSR303数据校验的错误都在这里

​			2）、没有模板引擎（模板引擎找不到这个错误页面），静态资源文件夹下找

​			3）、以上都没有错误页面，就会`默认来到SpringBoot默认的错误提示页面`



#### 	2、如何定制错误的json数据

>流程：
>
>1. 创建一个`exception.UserNotExistException`用户异常控制器，并 `extends RuntimeException`，并创建无参构造方法，并传一个报错信息。为了模拟报错，在控制器中对传递上来的参数进行判断，当参数为空，抛出自定义的异常`throw new UserNotExistException();`
>2. 新建控制器作为异常处理器`MyExceptionHandler`，定制异常的数据
>3. 将自定义的异常数据携带出去
>
>`spring boot 2 需要在配置文件加上`
>
>```properties
>server.error.include-exception=true
>server.error.include-message=always
>```
>
>

​	**自定义异常处理&返回定制json数据**

```java
/***
 * 异常处理器
 */
// 浏览器和客户端都是返回json
@ControllerAdvice
public class MyExceptionHandler {
    // 处理我们定义的异常
    @ResponseBody
    @ExceptionHandler(value = UserNotExistException.class)  // 指定处理的异常
    public Map<String, Object> handleException(Exception exception) {
        // 当监听到这个我们定义的异常 就会将异常数据传递进来
        Map<String, Object> map = new HashMap<>();
        map.put("code","user.notexist");
        map.put("message",exception.getMessage());
        return map;
    }
}

```



**转发到`/error`进行自适应响应效果处理**

> 使用这个方式的时候，一定要传入我们自定的错误码，`不然默认就是200，不会走到我们定制的错误模板，还是使用springboot默认的报错页`

```java
@ControllerAdvice
public class MyExceptionHandler {
    // 处理我们定义的异常
    @ExceptionHandler(value = UserNotExistException.class)
    public String handleException(Exception exception, HttpServletRequest request) {
        // 当监听到这个我们定义的异常 就会将异常数据传递进来
        Map<String, Object> map = new HashMap<>();

        // 传入我们的错误状态码
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE,500);
        map.put("code","user.notexist");
        map.put("message",exception.getMessage());
        // 并转发到/error
        // 这个时候 BasicErrorController 就会根据request 是html还是json 进行动态返回
        // 将额外添加的信息添加到一个属性中
        request.setAttribute("ext",map);
        return "forward:/error";
    }

}

```

#### 	3、将我们的定制数据携带出去

> 出现错误以后，会来到`/error`请求，会被`BasicErrorController`处理，响应出去可以获取的数据是由`getErrorAttributes`得到的（是`AbstractErrorController（ErrorController）`规定的方法）

​	1、~~完全来编写一个ErrorController的实现类【或者是编写AbstractErrorController的子类】，放在容器中；~~(过于繁杂)

​	2、页面上能用的数据，或者是json返回能用的数据都是通过
`errorAttributes.getErrorAttributes`得到

​			容器中`DefaultErrorAttributes.getErrorAttributes()`默认进行数据处理的

自定义ErrorAttributes

>创建`compotent.MyErrorAttributes`，并继承`DefaultErrorAttributes`重写`getErrorAttributes方法`

```java
/***
 * 给容器中添加我们自定义的 ErrorAttributes 去替换掉默认的 DefaultErrorAttributes
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String,Object> map =super.getErrorAttributes(webRequest, options);
        map.put("otherInfo","其他信息");
        return map;
    }
}

```

最终的效果：响应是自适应的，可以通过定制ErrorAttributes改变需要返回的内容

![](images\QQ截图20201203151216.png)

![](images\QQ截图20201203152406.png)





## 8、Demo

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
>`因为我把静态文件放在了/static/asserts 文件夹下`，所以通过, `"/asserts/**"`将该文件夹下的所有文件也放开
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
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index.html","/login","/user/login","/asserts/**");
            }
        };
        return webMvcConfigurer;
    }

}

```



### 5、抽取页面公共模块

> 在模板文件夹下，添加一个`commons`文件夹，用于将可复用的页面模块进行抽离
>
> 细节：左边导航栏模块，选中的需要高亮
>
> 通过 `th:class="${activeUri == 'main.html' ? 'nav-link active' : 'nav-link'}"` 进行判断当前活跃的url来给a便签动态添加 高亮的`calss`

**抽离出来的模块**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<!-- topBar -->
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0" th:fragment="nav-top-bar">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">[[${session.loginUser}]]</a>
    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">Sign out</a>
        </li>
    </ul>
</nav>


<!-- leftBar -->
<nav class="col-md-2 d-none d-md-block bg-light sidebar" th:fragment="nav-left-bar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link" href="#" th:href="@{/main.html}" th:class="${activeUri == 'main.html' ? 'nav-link active' : 'nav-link'}">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home">
                        <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                        <polyline points="9 22 9 12 15 12 15 22"></polyline>
                    </svg>
                    仪表盘 <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" th:href="@{/getEmps}" th:class="${activeUri == 'getEmps' ? 'nav-link active' : 'nav-link'}">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users">
                        <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                        <circle cx="9" cy="7" r="4"></circle>
                        <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
                        <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
                    </svg>
                    员工管理
                </a>
            </li>
        </ul>

    </div>
</nav>
</body>
</html>
```

**引用模块**

```html
<div th:replace="commons/bar::nav-top-bar"></div>

<!-- 这里同时将 -->
<div th:replace="commons/bar::nav-left-bar(activeUri ='main.html')"></div>
```









### 6、CRUD-员工

> 首先创建对应的控制器 `EmployeeController`，使用`@Controller`声明为控制器
>
> 将`dao`层进行注入  
>
> ``` bash
> @Autowired
> EmployeeDao employeeDao;
> ```
>
> 
>
> 创建方法 声明路由，并在方法内部，调用`dao`层的方法获取数据
>
> 将处理完成的数据结果放到请求域中，并返回页面模板

#### 查询列表

> 在模板文件夹`templates`文件夹下创建`emp` 文件夹，作为员工页面的指定文件夹
>
> 细节处理： `${#dates.format(emp.birth,'yyyy-MM-dd')}` 需要在配置文件中启用声明日期格式 `spring.mvc.format.date=yyyy-MM-dd`

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



``` html
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <h2><a href="emp" th:href="@{/emp}" class="btn btn-sm btn-success">添加员工</a></h2>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
                <tr>
                    <th>#</th>
                    <th>姓名</th>
                    <th>邮箱</th>
                    <th>性别</th>
                    <th>部门</th>
                    <th>出生日期</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="emp : ${employees}">
                    <td th:text="${emp.id}"></td>
                    <td th:text="${emp.lastName}"></td>
                    <td th:text="${emp.email}"></td>
                    <td th:text="${emp.gender} == 0 ? '女':'男'"></td>
                    <td th:text="${emp.department.departmentName}"></td>
                    <td th:text="${#dates.format(emp.birth,'yyyy-MM-dd')}"></td>
                    <td>
                        <!-- <a class="btn btn-sm btn-primary" th:href="@{/getEmpById/}+${emp.id}">编辑</a>-->
                        <a class="btn btn-sm btn-primary" th:href="@{/getEmpById(id=${emp.id})}">编辑</a>
                        <form th:action="@{/deleteEmp(id=${emp.id})}" method="post" style="display: inline-block;">
                            <input type="hidden" name="_method" value="delete">
                            <button type="submit" class="btn btn-sm btn-danger">删除</button>
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</main>

```

#### 新增 / 编辑 员工页

> 在模板文件夹`templates/emp`文件夹下新增`add.html`页面，编写新增表单
>
> 本着复用的原则，将新增和编辑页面作为同一个页面使用
>
> `在2.0版本后，开启 HiddenHttpMethodFilter，需要在配置文件中启用：spring.mvc.hiddenmethod.filter.enabled=true`



``` html
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

   <h2><a href="emp" th:href="@{/getEmps}" class="btn btn-sm btn-success">返回列表页</a></h2>

   <form th:action="@{updateEmp}" method="post">
      <!-- 发送put请求修改员工数据 -->
      <!--
		因为针对不同的行为，当新增调用的是post 而编辑调用的是put
		所以通过在 springMvc 中配置HiddenHttpMethodFilter
         页面创建一个post表单
         创建一个input项，让其隐藏，并设置 name="_method"声明是做请求方式的声明 value="put" 就是我们指定的请求方式
      -->
      <input type="hidden" name="_method" value="put" th:if="${empInfo!=null}">
      <input type="hidden" name="id" th:value="${empInfo.id}" th:if="${empInfo!=null}">
      <div class="form-group">
         <label>姓名</label>
         <input type="text" name="lastName" class="form-control" placeholder="请输入姓名" th:value="${empInfo!=null}? ${empInfo.lastName}">
      </div>
      <div class="form-group">
         <label>邮箱</label>
         <input type="email" name="email" class="form-control" placeholder="请输入邮箱" th:value="${empInfo!=null}? ${empInfo.email}">
      </div>
      <div class="form-group">
         <label>性别</label><br/>
         <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender" value="1" th:checked="${empInfo!=null}? ${empInfo.gender} == 1">
            <label class="form-check-label">男</label>
         </div>
         <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender" value="0" th:checked="${empInfo!=null}? ${empInfo.gender} == 0">
            <label class="form-check-label">女</label>
         </div>
      </div>
      <div class="form-group">
         <label>部门</label>
          <!-- 下拉选择中 通过遍历 option 标签，将value设置为id text设置为需要展示的名字 -->
          <!-- 在编辑功能中 将原来的数据进行选中 这个时候就需要去进行判断 判断当前页面中传进来的数据里面是否有 编辑的数据
			通过 selected	属性将当前数据下拉的数据对应的id 和默认下拉的id进行匹对 来进行默认选中
		-->
         <select class="form-control" name="department.id">
            <option th:each="dept:${depts}" th:value="${dept.id}" th:text="${dept.departmentName}" th:selected="${empInfo!=null} ? ${dept.id == empInfo.department.id}"></option>
         </select>
      </div>
      <div class="form-group">
         <label>出生日期</label>
         <input type="text" name="birth" class="form-control" placeholder="请输入出生日期" th:value="${empInfo!=null}? ${#dates.format(empInfo.birth,'yyyy-MM-dd')}">
      </div>
       <!-- 判断是否存在编辑数据 控制按钮的文字 -->
      <button type="submit" class="btn btn-primary" th:text="${empInfo!=null} ? '确认修改' : '添加'">添加</button>
   </form>
</main>
```



> 在编辑员工的时候，需要将当前行的id 也就是员工的id传到控制器中，控制器通过id查询数据，再将数据注入到页面中去。
>
> 这个时候需要在列表页中的编辑按钮绑定id，不同的绑定方式控制器在接收时候的方式也不一样
>
> ``` html
> <!-- 格式：/getEmpById?id=100  -->
> <a class="btn btn-sm btn-primary" th:href="@{/getEmpById(id=${emp.id})}">编辑</a>
> <!-- 控制器代码 -->
> <!-- 声明路由方式 -->
> @GetMapping("/getEmpById")
> <!-- 获取参数 -->
> @RequestParam("id") Integer id
> 
> 
> <!-- 格式：/getEmpById/100  -->
> <a class="btn btn-sm btn-primary" th:href="@{/getEmpById/}+${emp.id}">编辑</a>-->
> 
> <!-- 控制器代码 -->
> <!-- 声明路由方式 -->
> @GetMapping("/getEmpById/{id}")
> <!-- 获取参数 -->
> @PathVariable Integer id
> ```
>
> 
>
> ``` java
> 
> // 添加员工
> @PostMapping("/updateEmp")
> public String addEmp(Employee employee) {
>     System.out.println("保存的员工信息："+employee);
>     employeeDao.save(employee);
>     // 添加成功后 重定向到列表页
>     return "redirect:/getEmps";
> }
> 
> // 通过id获取员工信息
> @GetMapping("/getEmpById")
> //    @GetMapping("/getEmpById/{id}")
> public String getEmpById(@RequestParam("id") Integer id,Model model) {
>     //  @PathVariable Integer id
>     System.out.println("修改的员工信息的id"+id);
>     // 向页面传递员工数据
>     Employee employee = employeeDao.get(id);
>     model.addAttribute("empInfo",employee);
> 
>     // 页面中需要显示的选项
>     Collection<Department> departments = departmentDao.getDepartments();
>     model.addAttribute("depts",departments);
> 
>     System.out.println("返回给页面的数据"+employee);
>     return "emp/add";
> }
> 
> 
> // 员工修改
> @PutMapping("/updateEmp")
> public String updateEmployee(Employee employee) {
>     System.out.println("编辑提交的数据"+employee);
>     employeeDao.save(employee);
>     return "redirect:/getEmps";
> }
> ```
>
> 

#### 删除员工

``` java
// 员工删除
@DeleteMapping("/deleteEmp")
    public String deleteEmpById(@RequestParam("id") Integer id) {
    employeeDao.delete(id);
    return "redirect:/getEmps";
}
```

``` html
<form th:action="@{/deleteEmp(id=${emp.id})}" method="post" style="display: inline-block;">
    <input type="hidden" name="_method" value="delete">
    <button type="submit" class="btn btn-sm btn-danger">删除</button>
</form>
```

