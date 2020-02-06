# springmvc-step2
SpringMVC相关

1、MVC是一种架构模式   View  Controller  Model

2、Spring MVC是Spring体系的轻量级Web MVC框架，用来替代传统的J2EE的Servlet，来让我们开发web应用程序的时候更加简单

3、Spring MVC的核心Controller控制器，用于处理请求，产生响应

4、Spring MVC基于Spring IOC容器运行，所有对象被IOC管理

5、Spring 5.x版本变化

      *Spring 5.x最低要求JDK8与（Servlet 3.1/Tomcat 8.5+)
      *Spring 5.x支持JDK8/9,可以使用新特性
      *Spring 5.x最重要的新特性支持响应式编程：对某一事件做出响应
      
6、Spring MVC环境配置

         1.Maven依赖spring-webmvc
         2.web.xml配置DispatcherServlet
         3.配置applicationContext的mvc标记
         4.开发Controller控制器      

7、配置文件：
        
        web.xml:
        <?xml version="1.0" encoding="UTF-8"?>
        <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
                 version="3.1">
            <!--配置DispatcherServlet-->
            <servlet>
                <servlet-name>springmvc</servlet-name>
        
              <!--  DispatcherServlet是Spring MVC最核心的对象
                DispatcherServlet用于拦截Http请求，
                并根据请求的URL调用与之对应的Controller方法，来完成Http请求的处理
                -->
                <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        
               <!--还得告诉DispatcherServlet在IOC容器初始化时要加载
               哪个applicationContext.xml-->
                <init-param>
                    <param-name>contextConfigLocation</param-name>
                    <param-value>classpath:applicationContext.xml</param-value>
                </init-param>
        
                <!--在web应用启动时自动创建Spring IOC容器，并初始化DispatcherServlet，
                如果不配置，也会对IOC容器和DispatcherServlet进行初始化，只是实在第一次访
                问URL的时候对其进行初始化-->
                <load-on-startup>0</load-on-startup>
            </servlet>
        
            <servlet-mapping>
                <servlet-name>springmvc</servlet-name>
                <url-pattern>/</url-pattern>
            </servlet-mapping>
        
        
        </web-app>
         
         applicationContext.xml：
         <?xml version="1.0" encoding="UTF-8"?>
         <beans xmlns="http://www.springframework.org/schema/beans"
                xmlns:mvc="http://www.springframework.org/schema/mvc"
                xmlns:context="http://www.springframework.org/schema/context"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mv="http://www.springframework.org/schema/mvc"
                xsi:schemaLocation="http://www.springframework.org/schema/beans
                     http://www.springframework.org/schema/beans/spring-beans.xsd
                     http://www.springframework.org/schema/context
                     http://www.springframework.org/schema/context/spring-context.xsd
                     http://www.springframework.org/schema/mvc
                     http://www.springframework.org/schema/mvc/spring-mvc.xsd">
             <!--
            context:component-scan 标签作用
            在Spring IOC初始化过程中,自动创建并管理com.imooc.springmvc及子包中
            拥有以下注解的对象.
            @Repository
            @Service
            @Controller
            @Component
            -->
             <context:component-scan base-package="com.imooc.springmvc"/>
             <!--启用Spring MVC的注解开发模式-->
             <mvc:annotation-driven/>
             <!-- 将图片/JS/CSS等静态资源排除在外,可提高执行效率 -->
             <mvc:default-servlet-handler/>
         
         </beans>
         
8、URL Mapping

     （1）URL Mapping：将URL与Controller方法绑定，通过将URL与方法绑定，SpringMVC便可通过Tomcat对外
     暴露服务   
     （2）注解：
     @RequestMapping 通用绑定 ：作用在方法上，不区分get请求还是post请求；可以用在类上
     @GetMapping     绑定Get请求
     @PostMapping    绑定Post请求 
     
9、接受请求参数
      
     （1）使用Controller方法参数接收
     （2）使用Java Bean接收数据     
     
     参数类型：
       1、    @RequestParam List<Integer> 用集合接收复合参数需要加上 @RequestParam
       2、    Map作为参数有缺陷：如果提交的表单不包含复合数据即（复选数组数据），可以
                              用map来接收，但如果包含，map这能接收复合数据选的第一个值
       3、日期类型转换:将前端传过来的String转换成Date
          @DateTimeFormat(pattern = "yyyy-MM-dd") Date date                        
10、  URI相对路径与绝对路径
   
    说明： ./相对当前路径   
            URI：统一资源定位符，是URL的一个子集。
                 把主机和端口前半部分去掉 。 
        /project
           /js
              /jquery.js
           /index.js
           /index.html
           
           index.html-相对路径：   
           <script src="./index.js"></script>
           <script src="index.js"></script>
           <script src="./js/jquery.js"></script>
           
           index.html-绝对路径：   
           <script src="/project/index.js"></script>
           <script src="/project/js/jquery.js"></script>
  
           错误范例：
           <script src="/js/jquery.js"></script>
           http://localhost:8080/js/jquery.js  404

11、全局默认时间转换器
    
    （1）自定义时间转换器
    /**
     * 时间转换器：将String转换成日期类型
     */
    public class MyDateConverter implements Converter<String, Date> {
        public Date convert(String s) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(s);
                return date;
            } catch (ParseException e) {
                return null;
            }
        }
    }
    （2）将自定义的时间转换器配置在applicationContext.xml中
       <!--转换器Service、并将其注入到mvc:annotation-driven-->
        <bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
        <property name="converters">
                <set>
                    <bean class="com.imooc.springmvc.converter.MyDateConverter"/>
                </set>
        </property>
        </bean>
        
        <!--启用Spring MVC的注解开发模式-->
            <mvc:annotation-driven conversion-service="conversionService"/>
               
      
      
      
          