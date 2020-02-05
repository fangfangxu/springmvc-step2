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