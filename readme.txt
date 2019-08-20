1.先搭建mvc框架
引入spring-web，spring-webmvc；jar包，
引入javax.servlet.api；jar包
2.配置web.xml
springDemo中配置
	<display-name>spingDemo</display-name>
  	<servlet>
        <servlet-name>spring</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    </servlet>
	<servlet-mapping>
        <servlet-name>spring</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
<!--此时默认会将spring-servlet.xml当成spring的启动文件-->

spring-mybatis中web.xml配置
<!-- filter标签应该放在servlet前面，不然会报错，按报错顺序排列标签顺序 -->
  	<display-name>spring-mybatis</display-name>
    <filter>
	    <filter-name>encodingFilter</filter-name>
	    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
	    <init-param>
	        <param-name>encoding</param-name>
	        <param-value>UTF-8</param-value>
	    </init-param>
	</filter>
    <filter-mapping>
    	<filter-name>encodingFilter</filter-name>
    	<url-pattern>/*</url-pattern>
    </filter-mapping>
    <servlet>
        <servlet-name>spring</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:applicationContext.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
    </servlet>
	<servlet-mapping>
       <servlet-name>spring</servlet-name>
      <url-pattern>*.do</url-pattern>
    </servlet-mapping>
<!--此时classpath:applicationContext.xml将作为启动类加载，classpath为源文件src/main/resource-->
<!--applicationContext.xml配置扫描路径-->
<context:component-scan base-package="cn.ffcs.tsp" />  
3.编写controller文件
@Controller
@RequestMapping("/manualInfo")
//@Path
public class ManualInfoController {
写一个测试方法：
	@RequestMapping("/index.do")
	public String index(){
		System.out.println("11111");
		return "index" ;
	}
访问路径：http://localhost:8080/spring-mybatis/manualInfo/index.do，控制台输入11111，
4.页面显示，配置页面视图器
 <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
         <property name="prefix" value="/WEB-INF/jsp/"></property>
         <property name="suffix" value=".jsp"></property>
    </bean>
在/WEB-INF/jsp/创建index.jsp
则此时访问http://localhost:8080/spring-mybatis/manualInfo/index.do，会跳转到index页面，浏览器显示内容Hello World!
配置返回json格式交互，开启@ResponseBody注解
 <!-- springmvc4.0的配置方法-->
<!-- 启动注解驱动的mvc功能，json前后台交互-->
<mvc:annotation-driven content-negotiation-manager="contentNegotiationManager"/>
    <context:annotation-config/>
<bean id="contentNegotiationManager" class="org.springframework.web.accept.ContentNegotiationManagerFactoryBean">
    <property name="favorPathExtension" value="false"></property>
</bean>
<bean id="jsonConverter" class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"></bean>
<bean id="stringConverter" class="org.springframework.http.converter.StringHttpMessageConverter">
    <property name="supportedMediaTypes">
        <list>
            <value>text/plain;charset=UTF-8</value>
        </list>
    </property>
</bean>
<bean  class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
    <property name="messageConverters">
        <list>
            <ref bean="jsonConverter"/>
            <ref bean="stringConverter"/>
        </list>
    </property>
</bean>
ManualInfoController文件添加getObj方法
 	 @RequestMapping("/getObj")
	 @ResponseBody
	 public ManualInfo getObj(){
		 ManualInfo m = new ManualInfo() ;
		 m.setDowloadUrl("11111");
		 m.setManualName("");
		 System.out.println("11111");
		return m;
	 }
访问路径：http://localhost:8080/spring-mybatis/manualInfo/getObj.do
返回json格式：{"id":null,"manualName":"","manualDesc":null,"dowloadUrl":"11111","autoEnterpriseCode":null,"updateDate":null,"manualType":null}
---------------到此处springmvc框架完成搭建
==================================================================
---------------spring+mybatis开始搭建
5.<引入mybatis的jar包-->
	<!-- mybatis start -->
  		<dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.2.8</version>
  		</dependency>
  		 <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.2.2</version>
        </dependency>
  	<!-- mybatis end -->
  	
6.<!--引入数据库连接池jar包与mysql驱动包-->
	<!--alibaba 数据库连接池 start-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.12</version>
        </dependency>
  	<!-- alibaba 数据库连接池 end -->
  	  <!-- mysql驱动包  start-->  
         <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.0.8</version>
        </dependency>
      <!-- mysql驱动包 end --> 
8.<!--引入spring jar包 -->
<!--Spring start -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.1.4.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>4.1.4.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>4.1.4.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>4.1.4.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>4.1.4.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>aspectj.weaver</groupId>
            <artifactId>aspectj</artifactId>
            <version>4.1</version>
        </dependency>
<!-- spring end -->
9.创建classpath:jdbc.properties文件
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/semi?characterEncoding=UTF-8&serverTimezone=UTC
jdbc.username=root
jdbc.password=123456
jdbc.configLocation=mysql

10.创建classpath:spring.xml文件
<context:property-placeholder location="classpath:configs/jdbc.porperties"/>
    
    <!-- dataSource 配置-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    	<property name="driverClassName" value="${jdbc.driverClassName}"></property>
		<property name="url" value="${jdbc.url}"></property>
		<property name="username" value="${jdbc.username}"></property>
		<property name="password" value="${jdbc.password}"></property>
		  <!-- 配置初始化大小、最小、最大 -->
        <property name="initialSize" value="10" />
        <property name="minIdle" value="10" />
        <property name="maxActive" value="20" />
 
        <!-- 配置获取连接等待超时的时间 -->
        <property name="maxWait" value="60000" />
 
        <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
        <property name="timeBetweenEvictionRunsMillis" value="60000" />
 
        <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
        <property name="minEvictableIdleTimeMillis" value="300000" />
 
        <property name="validationQuery" value="SELECT 'x'" />
        <property name="testWhileIdle" value="true" />
        <property name="testOnBorrow" value="false" />
        <property name="testOnReturn" value="false" />
 
        <!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
        <property name="poolPreparedStatements" value="false" />
        <property name="maxPoolPreparedStatementPerConnectionSize" value="20" />
 
        <!-- 配置监控统计拦截的filters -->
        <property name="filters" value="stat" />
    </bean>
    
      <!-- 事务管理器 -->
	<bean id="transactionmanager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
	</bean>
	
	<!-- 方法事务参数 -->
	<tx:advice id="serviceAdvice" transaction-manager="transactionmanager">
		<tx:attributes>
			<tx:method name="save*" propagation="REQUIRED" rollback-for="Throwable"/>
			<tx:method name="update*" propagation="REQUIRED" rollback-for="Throwable"/>
			<tx:method name="del*" propagation="REQUIRED" rollback-for="Throwable"/>
			<tx:method name="*" read-only="true"/>
		</tx:attributes>
	</tx:advice>
	
	<!-- 配置切面 -->
	<aop:config proxy-target-class="true">
		<aop:pointcut id="servicePointcut" expression="execution(* cn.ffcs.tsp.msa.mybatis.service.*.*(..))"/>
		<aop:advisor pointcut-ref="servicePointcut" advice-ref="serviceAdvice"/>
	</aop:config>
11.创建classpath:spring-mybatis.xml文件
<!-- 配置sqlSessionFactoryBean 需要指明class-->
    <bean id ="sessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    	<!-- 配置数据库 -->
    	<property name="dataSource" ref="dataSource"/>
    	<!-- 引入mybatis文件 -->
    	<property name="configLocation" value="classpath:configs/mybatis-config.xml"/>
    	<!-- 加载mapper.xml文件  -->
    	<property name="mapperLocations" value="classpath:configs/mappers/${jdbc.configLocation}/*.xml"/>
    	<!-- 配置返回的pojo指向的路径 -->
    	<property name="typeAliasesPackage" value="cn.ffcs.tsp.msa.mybatis.entity"/>
    </bean>
     	
     <import resource="classpath:configs/mybatis-config.xml"/>
     <!-- 通过扫描的模式，扫描目录在com/hoo/mapper目录下，所有的mapper都继承SqlMapper接口的接口， 这样一个bean就可以了 -->  
    <bean  class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="sqlSessionFactoryBeanName" value="sessionFactory"/>
          <property name="basePackage" value="cn.ffcs.tsp.msa.mybatis.mapper"/>
    </bean>
    
12.创建mybatis-config.xml文件
<configuration>
	<settings>
	  <!-- 该配置影响的所有映射器中配置的缓存的全局开关。默认值true -->
	  <setting name="cacheEnabled" value="true"/>
	  <!--延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置fetchType属性来覆盖该项的开关状态。默认值false  -->
	  <setting name="lazyLoadingEnabled" value="false"/>
	  <!-- 是否允许单一语句返回多结果集（需要兼容驱动）。 默认值true -->
	  <setting name="multipleResultSetsEnabled" value="true"/>
	  <!-- 使用列标签代替列名。不同的驱动在这方面会有不同的表现， 具体可参考相关驱动文档或通过测试这两种不同的模式来观察所用驱动的结果。默认值true -->
	  <setting name="useColumnLabel" value="true"/>
	  <!-- 允许 JDBC 支持自动生成主键，需要驱动兼容。 如果设置为 true 则这个设置强制使用自动生成主键，尽管一些驱动不能兼容但仍可正常工作（比如 Derby）。 默认值false  -->
	  <setting name="useGeneratedKeys" value="false"/>
	  <!--  指定 MyBatis 应如何自动映射列到字段或属性。 NONE 表示取消自动映射；PARTIAL 只会自动映射没有定义嵌套结果集映射的结果集。 FULL 会自动映射任意复杂的结果集（无论是否嵌套）。 --> 
	  <!-- 默认值PARTIAL -->
	  <setting name="autoMappingBehavior" value="PARTIAL"/>
	  
	  <!--<setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>  -->
	 <!--  配置默认的执行器。SIMPLE 就是普通的执行器；REUSE 执行器会重用预处理语句（prepared statements）； BATCH 执行器将重用语句并执行批量更新。默认SIMPLE  -->
	  <setting name="defaultExecutorType" value="SIMPLE"/>
	  <!-- 设置超时时间，它决定驱动等待数据库响应的秒数。 -->
	  <setting name="defaultStatementTimeout" value="25"/>
	  
	  <!--<setting name="defaultFetchSize" value="100"/>  -->
	  <!-- 允许在嵌套语句中使用分页（RowBounds）默认值False -->
	  <setting name="safeRowBoundsEnabled" value="false"/>
	  <!-- 是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典 Java 属性名 aColumn 的类似映射。  默认false -->
	  <setting name="mapUnderscoreToCamelCase" value="false"/>
	  <!-- MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。
	  		 默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。
	   		若设置值为 STATEMENT，本地会话仅用在语句执行上，对相同 SqlSession 的不同调用将不会共享数据。  -->
	  <setting name="localCacheScope" value="SESSION"/>
	  <!-- 当没有为参数提供特定的 JDBC 类型时，为空值指定 JDBC 类型。 某些驱动需要指定列的 JDBC 类型，多数情况直接用一般类型即可，比如 NULL、VARCHAR 或 OTHER。  -->
	  <setting name="jdbcTypeForNull" value="OTHER"/>
	  <!--   指定哪个对象的方法触发一次延迟加载。  -->
	  <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
	  <!-- 打印查询语句 -->
       <setting name="logImpl" value="STDOUT_LOGGING" />
	</settings>
</configuration>

13.创建mapper.xml
14.创建mapper对应的mapperInterface.java
15.创建mybatis测试类

遇到问题：通配符的匹配很全面, 但无法找到元素 'mvc:annotation-driven'的声明。
原因是：虽然在xml文件上方声明了mvc，但没有配置此声明对应的文件信息，正确配置如下：
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:context="http://www.springframework.org/schema/context"
xmlns:mvc="http://www.springframework.org/schema/mvc"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd ">--原因此行缺失


遇到问题：mybatis-config.xml路径拷贝错误，找不到路径
遇到问题mapper.xml中namespace与mapperInterface未对应
==================mapperInferce单独建立TestApplication测试成功，成功连接数据库，mybatis搭建成功
======项目补充完整@Controller，@Service，不需要声明@Reposity


启动方式：1.java编写jetty启动类启动，2.tomcat启动方式
jetty启动需要引入jettyjar包，而且需要删除javax.servlet.api的jar包，否则报javax.servlet.http.HttpSessionIdListener

//继续集成redis框架
首先导入spring-data-redishe和redis.client的jar包
redis.client如果2.4版本以上，redis.maxActive需要用redis.maxTotal代替，maxWait改成maxWaitMillis
<context:property-placeholder location="classpath:jdbc.porperties"/>，该标签只能在一个xml中被使用，目前redis和jdbc都配置在了jdbc.properties中。
版本用的spring-data-redishe 1.6.2，redis.client 2.9.0，调试期间一直报这个错，两个版本有些冲突org.springframework.core.serializer.support.DeserializingConverter.<init>(Ljava/lang/ClassLoader;)V
添加spring-redis配置文件，主要配置poolConfig，连接池配置，jedisConnectionFactory，redis缓存连接信息配置，redisTemplate配置，记得进行序列化
否则 存入redis的key为类似这样的形式"\xac\xed\x00\x05t\x00\x04test"，spring-redis集成完毕
spring-data-redis 1.6.2不支持集群操作，需要引入1.7.0


//集成mongodb
添加jar包依赖
<dependency>
         <groupId>org.mongodb</groupId>
         <artifactId>mongo-java-driver</artifactId>
         <version>2.13.0-rc0</version>
     </dependency>

     <dependency>
         <groupId>org.springframework.data</groupId>
         <artifactId>spring-data-mongodb</artifactId>
         <version>1.7.1.RELEASE</version>
     </dependency>
     <dependency>
         <groupId>org.springframework.data</groupId>
         <artifactId>spring-data-mongodb-cross-store</artifactId>
         <version>1.7.1.RELEASE</version>
     </dependency>
     <dependency>
         <groupId>org.springframework.data</groupId>
         <artifactId>spring-data-mongodb-log4j</artifactId>
         <version>1.7.1.RELEASE</version>
     </dependency>
配置spring-mongo.xml
配置mongo数据源
利用org.springframework.data.mongodb.core.MongoTemplate 进行增删改查操作
集成hbase
添加依赖jar包