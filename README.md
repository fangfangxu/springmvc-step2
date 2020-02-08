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
               
12、Web应用中的中文乱码问题

    Web应用中的中文乱码由来
    *Tomcat默认使用字符集ISO-8859-1,属于西欧字符集
    *解决乱码的核心思路是将ISO-8859-1转换为UTF-8
    *Controller中请求与响应都需要设置UTF-8字符集    
      
    中文乱码的配置：
    Get请求乱码-server.xml增加URIEncoding属性 tomcat8以后自己默认为UTF-8字符集，
          tomcat8以前要设置UTF-8,这儿要根据版本区分对待
    Post请求乱码-web.xml配置CharacterEncodingFilter
    Response响应乱码-Spring配置StringHttpMessageConverter       
    
    eg：
    Post请求乱码-web.xml配置CharacterEncodingFilter
       <!--配置post请求字符集-->
        <filter>
            <filter-name>characterFilter</filter-name>
            <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
            <init-param>
                <param-name>encoding</param-name>
                <param-value>UTF-8</param-value>
            </init-param>
        </filter>
        <filter-mapping>
            <filter-name>characterFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>
        
        eg:
        Response响应乱码-Spring配置StringHttpMessageConverter  (消息转换器)
           
            <!--启用Spring MVC的注解开发模式-->
            <mvc:annotation-driven conversion-service="conversionService">
                <mvc:message-converters>
                    <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                        <property name="supportedMediaTypes">
                            <list>
                                <!--response.setContentType("text/html;charset=utf-8");-->
                                <value>text/html;charset=utf-8</value>
                            </list>
                        </property>
                    </bean>
                </mvc:message-converters>
            </mvc:annotation-driven>
            
13、响应对外输出结果
    
     一、@ResponseBody - 产生响应文本 
     二、ModelAndView  - / 从应用的根路径（前边加斜线是绝对路径，不加是相对-即针对当前Contrller）
                       - 利用模板引擎（jsp、freemarker..）渲染输出Model数据对象
                       - SpringMVC中默认的模板引擎是就是jsp
                       -反应了MVC的设计理念，让数据和页面的展现进行解耦
                       -mav.addObject()方法设置的属性默认存放在当前请求中
                       -默认new ModelAndView("/view.jsp")使用请求转发（forward）至页面
                       -重定向使用 new ModelAndView（"redirect:/index.jsp"）
     三、
     请求转发：地址栏不变、Controller和页面共享一个Request对象
     重定向：地址栏变化、Controller通知客户端浏览器重新建立一个新的R
             equest来访问新的地址
     
     四、扩展
     ModelAndView=String+ModelMap
     
         //String与ModelMap
         //Controller方法返回String的情况
         //1. 方法被@ResponseBody描述，SpringMVC直接响应String字符串本身
         //2. 方法不存在@ResponseBody，则SpringMVC处理String指代的视图（页面）
         @GetMapping("/view1")
         public String showView(Integer id, ModelMap model){
             String viewName="/view.jsp";
             User user = new User();
             if (id == 1) {
                 user.setUsername("wangyx");
             } else {
                 user.setUsername("xuff");
             }
             model.addAttribute("u",user);
             return viewName;
         }
     五、   
         举例一：
         @GetMapping("/view")
         public ModelAndView showView(Integer id) {
             //  "/"此处是从应用的根路径进行访问
             ModelAndView mav = new ModelAndView("/view.jsp");
             User user = new User();
             if (id == 1) {
                 user.setUsername("wangyx");
             } else {
                 user.setUsername("xuff");
             }
             mav.addObject("u", user);
             return mav;
         }
 14、SpringMVC整合Freemarker     
  
     （1）pom中引入依赖     
         freemarker、spring-context-support   
     （2）启用Freemarker模板引擎（设置渲染后向客户端输出的字符集、后缀）
     
       <!--视图解析器：配置Freemarker引擎-->
         <bean id="ViewResolver" class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
             <!--指定FreeMarker模板文件扩展名-->
             <property name="suffix" value=".ftl"></property>
             <!--设置响应输出，并解决中文乱码-->
             <property name="contentType" value="text/html;charset=utf-8"></property>
         </bean>
         
     （3）配置Freemarker参数 （设置渲染时的字符集、模板加载的根路径）  
     
       <bean id="freeMarkerConfig" class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
               <!--设置模板保存的目录-->
               <property name="templateLoaderPath" value="/WEB-INF/ftl"></property>
               <!--模板引擎其他设置-->
               <property name="freemarkerSettings">
                 <props>
                     <!--设置Freemarker脚本与数据渲染时使用的字符集-->
                     <prop key="defaultEncoding">UTF-8</prop>
                 </props>
             </property>
         </bean>

15、REST与RESTful
      
      （一）
      REST-表现层状态转换，资源在网络中以某种表现形式进行状态转移
      RESTful-是基于REST理念的一套开发风格（前后端分离），是具体的开发规则
      
        解释：（传统Web应用的问题：mvc最后渲染输出的是html，那么客户端必然是支持html
        的浏览器，目前互联网的发展呈多元化趋势，除了IE这种B/S下的传统客户端，
        还有比如微信小程序、app各种各样的客户端，像这样的客户端是不支持html的，
        那么我们想app等客户端也能和后端进行通信那该怎么办呢？那么一种全新的开发
        风格产生了，就是RESRTful开发风格）
      
      RESTful开发规范
      使用URL作为用户交互入口
      明确的语义规范(GET|POST|PUT|DELETE)
      只返回数据（JSON|XML）,不包含任何展现        
     （二） 
      简单demo：
          <script src="jquery-3.3.1.min.js"></script>
          <script>
              $(function(){
                  $("#btnGet").click(function () {
                      $.ajax({
                          url : "/restful/request",
                          type : "get" ,
                          dataType : "json" ,
                          success : function(json){
                              $("#message").text(json.message);
                          }
                      })
                  });
              })
              </script>
              
              @Controller
              @RequestMapping("/restful")
              public class RestfulController {        
                @GetMapping("/request")
                @ResponseBody
                public String doGetRequest(){
                    return "{\"message\":\"徐doGetRequest\"}";
                }            
            }
            
     （三） RestController注解与路径变量（存放在URI中可变的一部分数值）
       
       （1）每一个方法上都写@ResponseBody很麻烦，Spring4后提供了RestController
            注解，类上加上该注解，默认当前类中的方法返回的都是Rest形式的数据而不是
            页面的跳转。RestController单纯就是为了简化开发的，没有任何别的作用

       （2）路径变量
          JS：
               $(function(){
                   $("#btnPost").click(function () {
                       $.ajax({
                           url : "/restful/request/1024",
                           type : "post" ,
                           dataType : "json" ,
                           success : function(json){
                               $("#message").text(json.message+","+json.rid);
                           }
                       })
                   });
               })
               
          JAVA:     
          @PostMapping("/request/{rid}")
           @ResponseBody
           public String doPostRequest(@PathVariable("rid") Integer id){
               return "{\"message\":\"徐doPostRequest\",\"rid\":"+id+"}";
           }
           
      （四）简单请求与非简单请求
           简单请求：是指标准结构的HTTP请求，对应GET/POST请求
           非简单请求： 复杂要求的HTTP请求，PUT/DELETE、扩展标准请求（GET/POST请求扩展了额外的自定义请求头）
                        
           两者最大区别是非简单请求发送前需要发送预检请求
           
           最早的SpringMVC是为我们网页服务的，默认网页提交的时候只支持两种get和post，对于非简单请求，SpringMVC
           不能改原有代码，只能采取折中方案来解决该问题，提供了表单内容过滤器；
           -FormContentFilter:是对SpringMVC能力的扩展，否则无法支持PUT/DELETE方式传递参数
           
               <!--SpringMVC中支持复杂请求传递参数的过滤器-->
               <filter>
                   <filter-name>formContentFilter</filter-name>
                   <filter-class>org.springframework.web.filter.FormContentFilter</filter-class>
               </filter>
           
               <filter-mapping>
                   <filter-name>formContentFilter</filter-name>
                   <url-pattern>/*</url-pattern>
               </filter-mapping>
           
      （五）Json序列化
         1、引入依赖
         jackson-core（2.9以后版本，否则有安全风险）2.9.9
         jackson-databind
         jackson-annotations
            
         2、Controller方法直接返回实体类型：
         @GetMapping("/persons")
             public List<Person> findPersons(){
                 List<Person> list=new ArrayList<Person>();
                 Person person1=new Person();
                 person1.setName("wang宇");
                 person1.setAge(10);
                 person1.setDate(new Date());
                 Person person2=new Person();
                 person2.setName("xuf");
                 person2.setAge(10);
                 person2.setDate(new Date());
                 list.add(person2);
                 list.add(person1);
                 return list;
             }
             
         实体类处理：
           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
             private Date date;
         3、
               $(function () {
                     $("#quertBtns").click(function () {
                         $.ajax({
                             url: "/restful/persons",
                             type: "get",
                             dataType: "json",
                             success: function (json) {
                                 for (var i = 0; i < json.length; i++) {
                                     var p = json[i];
                                     $("#message2").append("<h2>" + p.name + "-" + p.age + "-" + p.date + "</h2>")
                                 }
         
                             }
                         })
                     });
                 })           
                 
      （六）浏览器的同源策略
       1、同源策略：阻止一个域加载的脚本去获取另一个域上的资源             
       2、只要协议、域名、端口有任何一个不同，都被当做是不同的域
       3、如果出现跨域问题，在浏览器的Console就会出现 Access-Control-Allow-Origin 的报错，
       这个错误告诉我们触发了同源策略，请求得到的结果并不会被浏览器处理
       4、HTML中允许跨域的标签：<img><script><link>
       
      （七）SpringMVC跨域访问：CrossOrigin注解解决跨域访问
            1、CORS跨域资源访问
            *CORS是一种机制，使用额外的HTTP头通知浏览器可以访问其他域
            *URL响应头包含Access-Control-*指明请求允许跨域
            2、
           解决跨域的两种方式：
            1.注解@CrossOrigin-Controller跨域注解
                         http://localhost:80页面  访问  http://localhost:8080服务器接口属于跨域
                         在8080端：
                         @CrossOrigin(origins = "http://localhost",maxAge = 3600)
                                           maxAge:设置预检请求的缓存时间为1小时，非简单请求
                                             （PUT/DELETE）客户端向服务器发送请求会经过预检请求、
                                              真实请求两个，那么设置maxAge后，同样的PUT/DELETE
                                              再次发送请求，在1小时内，不会经过预检请求，直接发送
                                              真实请求，这样会帮助我们缓解服务器压力，maxAge是将我们
                                              预检请求的处理结果进行缓存。
         
            2、<mvc:cors>-Spring MVC全局跨域配置
            
                    <!--配置浏览器跨域访问问题-->
                    <mvc:cors>
                        <mvc:mapping path="/restful/*"
                                     allowed-origins="http://localhost"
                                     max-age="3600"/>
                    </mvc:cors>
      
           以上应用专用于WEBAPI、不适用app、微信小程序，也就是只对外提供WEB数据服务的
           话选择全局跨域配置；如果只是个别controller对外暴露服务的话选择注解；
           如果两种方式都用了的话，以注解方式为准
           
16、SpringMVC标准组件：拦截器-Interceptor
      
       Interceptor被创建后是天然的运行在Spring IOC容器中的
     （一）
     拦截器-Interceptor
     *拦截器(Interceptor)用于对URL请求进行前置/后置过滤
     *Interceptor与Filter(J2EE标准组件)用途相似，但实现方式不同
     *Interceptor底层就是基于Spring AOP面向切面编程实现
     
     拦截器开发流程
     1.Maven依赖servlet-api 3.1.0
   
     2.实现HandlerInterceptor接口
       *preHandle -前置执行处理：在HandlerAdapter.handle()方法之前被调用
       *postHandle -目标资源已被Spring MVC框架处理：在HandlerAdapter.handle()方法之后，视图渲染之前被调用
       *afterCompletion -响应文本已经产生：视图渲染之后被调用
       
       public class MyInterceptor implements HandlerInterceptor {
           public boolean preHandle(HttpServletRequest request, 
                                    HttpServletResponse response, Object handler) throws Exception {
              System.out.println(request.getRequestURL()+
                      "-前置处理:在HandlerAdapter.handle()方法之前被调用");
              return true;
           }
       
           public void postHandle(HttpServletRequest request, 
                                  HttpServletResponse response, Object handler, 
                                  ModelAndView modelAndView) throws Exception {
               System.out.println(request.getRequestURL()+
                       "-目标处理成功：在HandlerAdapter.handle()方法之后，视图渲染之前被调用");
           }
       
           public void afterCompletion(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Object handler, Exception ex) throws Exception {
               System.out.println(request.getRequestURL()+
                       "-响应内容已产生：视图渲染之后被调用");
           }
       }
       
     3.applicationContext配置过滤地址      
           <!--配置拦截器-->
           <mvc:interceptors>
               <mvc:interceptor>
                   <mvc:mapping path="/**"/>
                   <bean class="com.imooc.restful.interceptor.MyInterceptor"/>
               </mvc:interceptor>
           </mvc:interceptors> 