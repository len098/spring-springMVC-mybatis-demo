# spring-springMVC-mybatis-demo
一个小demo，整合了ssm，对之前学习做一个小整合
# 一个简单的SSM框架整合教程

整理一下最近学的三大框架，做一个最简单的整合

首先在idea建立一个maven的空项目，使用maven的原因是maven可以帮我们省去一个个导入jar包的操作，使用起来也很是方便，因为要更好的进行一次复习，也就不使用里边的模板了，也好重新熟悉熟悉里面的各种文件结构。



### 项目文件基本结构

![1552916877075](C:\Users\Len\AppData\Roaming\Typora\typora-user-images\1552916877075.png)

- controller-控制层
- dao-持久层
- service-业务层
- entity-实体类
- resources-放置各种配置文件
  - mapper-存放MybatisMapper
- webapp-放置与web相关的文件
  - static-放置静态文件

#### 需要用到的库

- Spring+SpringMVC+MyBatis
- servlet+jsp+jstl
- Junit
- druid-数据库连接池，比较习惯用这个
- fastjson-阿里的json解析，使用方便，后端向前端发送数据时可以利用json格式进行
- lombok-很强大的注解，idea使用需要加多一个lombok的插件，设置里边找一下插件，然后下载重启idea即可

pom.xml文件配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.len</groupId>
    <artifactId>ssm</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <java.version>1.8</java.version>
        <spring.version>5.0.8.RELEASE</spring.version>
        <cglib.version>3.2.5</cglib.version>
    </properties>

    <!--依赖 dependencies-->
    <dependencies>
        <!-- Spring core & mvc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-orm</artifactId>
            <version>${spring.version}</version>
            <type>jar</type>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
            <type>jar</type>
            <scope>test</scope>
        </dependency>

        <!-- Servlet Spec -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.4</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <artifactId>junit-jupiter-api</artifactId>
            <groupId>org.junit.jupiter</groupId>
            <version>5.3.2</version>
            <scope>compile</scope>
        </dependency>

        <!--junit-->
        <!-- https://mvnrepository.com/artifact/junit/junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.3.1</version>
            <scope>test</scope>
        </dependency>

        <!--druid连接池-->
        <dependency>
            <artifactId>druid</artifactId>
            <groupId>com.alibaba</groupId>
            <version>1.1.12</version>
        </dependency>
        
        <!--mybatis-->
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.0</version>
        </dependency>
        <!--mybatis-Spring-->
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.2</version>
        </dependency>
        <!--  jdbc驱动包 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.13</version>
        </dependency>

        <!--lombok-->
        <dependency>
            <artifactId>lombok</artifactId>
            <groupId>org.projectlombok</groupId>
            <version>1.18.4</version>
        </dependency>
        <!--&lt;!&ndash;JSON依赖&ndash;&gt;-->
        <!--&lt;!&ndash; https://mvnrepository.com/artifact/org.json/json &ndash;&gt;-->
        <!--<dependency>-->
            <!--<groupId>org.json</groupId>-->
            <!--<artifactId>json</artifactId>-->
            <!--<version>20160810</version>-->
        <!--</dependency>-->

        <!--阿里的fastjson-->
        <!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.54</version>
        </dependency>

        <!-- JSTL标签类 -->
        <dependency>
            <groupId>jstl</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <dependency>
            <artifactId>spring-test</artifactId>
            <groupId>org.springframework</groupId>
            <version>5.1.5.RELEASE</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <artifactId>junit-jupiter-api</artifactId>
            <groupId>org.junit.jupiter</groupId>
            <version>5.3.2</version>
        </dependency>
    </dependencies>

</project>
```



web.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
         http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         id="WebApp_ID" version="3.0">

    <!--spring配置文件路径-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-config.xml</param-value>
    </context-param>
    <!-- 配置spring监听器-->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <!--spring mvc前端控制器-->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 初始化参数配置 spring-mvc配置文件路径-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    
    <!-- 编码过滤器 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <async-supported>true</async-supported>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```



spring-mvc.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p" xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context.xsd
http://www.springframework.org/schema/mvc
http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 扫描所有ssm.controller包下面的所有controller -->
    <context:component-scan base-package="ssm.controller"/>
    <!-- 开启注解配置-->
    <mvc:annotation-driven/>
    <!-- 静态资源解析，包括js,css,img... -->
    <mvc:resources mapping="/**" location="/"/>
    <!-- 配置视图解析器-->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!--视图前缀-->
        <property name="prefix" value="/"/>
        <!--视图后缀-->
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```



spring-config.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd"
       >
    <!--使用properity文件-->
    <context:property-placeholder location="classpath:db.properties" system-properties-mode="NEVER"/>
    <!--配置扫描-->
    <context:component-scan base-package="ssm"/>
    <!-- 配置druid数据源 -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
          init-method="init" destroy-method="close">
        <property name="driverClassName" value="${driverClassName}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
    </bean>

    <!-- 配置mybatis的sqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!-- 自动扫描mapper.xml文件-->
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
        <!-- 载入mybatis全局配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>

    <!-- 配置mybatis dao接口扫描-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="ssm.dao"/>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    </bean>

         <!--配置spring的声明式事务，名字为默认值 -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource" />
    </bean>

         <!--开启事务控制的注解支持 -->
    <tx:annotation-driven transaction-manager="transactionManager"/>
</beans>
```



mybatis-config.xml配置

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 别名 -->
    <typeAliases>
        <package name="ssm.entity"/>
    </typeAliases>
</configuration>
```



使用properties文件存放数据库的数据，方便以后更改

db.properties配置

```properties
#key=value
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/
username=root
password=1024

```



log4j.properties配置

```properties
log4j.rootLogger=DEBUG, Console  

#Console
log4j.appender.Console=org.apache.log4j.ConsoleAppender  
log4j.appender.Console.layout=org.apache.log4j.PatternLayout  
log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n  

log4j.logger.java.sql.ResultSet=INFO  
log4j.logger.org.apache=INFO  
log4j.logger.java.sql.Connection=DEBUG  
log4j.logger.java.sql.Statement=DEBUG  
log4j.logger.java.sql.PreparedStatement=DEBUG
```







新建一个数据库

建立一个test 的表，如下，作为测试用

```mysql
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `age` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

新建一个实体类TestEntity

```java
package ssm.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Data //use lombok
@Component //注册为bean
public class TestEntity implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
```

service层，新建一个TestService接口以及其实现类，因为为测试用，暂且不需要往里边填充东西，留着以后可用

```java
package ssm.service.impl;
//TestServiceImpl实现类
public class TestServiceImpl {
}

package ssm.service;
//TestService接口类
public interface TestService {
}
```



dao层，新建一个接口，查询数据库信息用

```java
package ssm.dao;

import org.springframework.stereotype.Repository;
import ssm.entity.TestEntity;

@Repository //注册为持久层的bean
public interface TestDao {
        /**
         * 查询数据库信息
         */
        TestEntity getData();
}
```



在mapper中新建一个TestMapper.xml文件，对dao层中接口的映射以及实现

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <!--Mybatis动态代理进行映射dao接口-->
<mapper namespace="ssm.dao.TestDao">
    <!--
        返回结果映射
        如果开启驼峰命名转换且命名规范该段代码可以不写
        因为配置别名 所以type属性使用的是mybatis-conf中的别名
        -->
    <resultMap type="TestEntity" id="TestResult">
        <id property="id" column="id"/>
        <result property="name" column="name"/>
        <result property="age" column="age"/>
    </resultMap>
    <!--  定义字段集合 -->
    <sql id="TestColumns">
        id,name,age
    </sql>
    <!-- 接口中getData 具体实现-->
    <select id="getData" resultMap="TestResult">
        select <include refid="TestColumns"/>  from test where id = 1
    </select>
</mapper>
```



然后建立一个测试类App，对Spring-MyBtis的整合进行测试

```java
package ssm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ssm.dao.TestDao;
import ssm.entity.TestEntity;

//使用spring5的测试
@SpringJUnitConfig(locations = "file:src/main/resources/spring-config.xml")
public class App {
    @Autowired
    private TestDao testdao;

    @Test
    void getData1() throws Exception{
        TestEntity data = testdao.getData();
        System.out.println(data);
    }
}
```

运行，出现下图则表示整合成功

![1552917602944](C:\Users\Len\AppData\Roaming\Typora\typora-user-images\1552917602944.png)



接着，对Spring-SpringMVC进行测试

在controller层中新建一个TestController类，返回一个json格式

```java
package ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  //注册为控制器bean
@RequestMapping(value = "/test")    //请求路径
public class TestController {
        @ResponseBody  //返回json数据
        @RequestMapping(value="/hello",produces="application/json;charset=UTF-8")
        public String hello(){
            return "Hello!你好呀，SSM";
        }
}
```



配置好Tomcat，在浏览器输入` http://localhost:8080/ssm_war_exploded/test/hello`出现下图，

![1552922867982](C:\Users\Len\AppData\Roaming\Typora\typora-user-images\1552922867982.png)

则恭喜你，到现在ssm三件套已经整合完毕，在这个基础上可以进行进一步的开发了
