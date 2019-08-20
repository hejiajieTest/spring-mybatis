1.�ȴmvc���
����spring-web��spring-webmvc��jar����
����javax.servlet.api��jar��
2.����web.xml
springDemo������
	<display-name>spingDemo</display-name>
  	<servlet>
        <servlet-name>spring</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    </servlet>
	<servlet-mapping>
        <servlet-name>spring</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
<!--��ʱĬ�ϻὫspring-servlet.xml����spring�������ļ�-->

spring-mybatis��web.xml����
<!-- filter��ǩӦ�÷���servletǰ�棬��Ȼ�ᱨ��������˳�����б�ǩ˳�� -->
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
<!--��ʱclasspath:applicationContext.xml����Ϊ��������أ�classpathΪԴ�ļ�src/main/resource-->
<!--applicationContext.xml����ɨ��·��-->
<context:component-scan base-package="cn.ffcs.tsp" />  
3.��дcontroller�ļ�
@Controller
@RequestMapping("/manualInfo")
//@Path
public class ManualInfoController {
дһ�����Է�����
	@RequestMapping("/index.do")
	public String index(){
		System.out.println("11111");
		return "index" ;
	}
����·����http://localhost:8080/spring-mybatis/manualInfo/index.do������̨����11111��
4.ҳ����ʾ������ҳ����ͼ��
 <!-- ��ͼ������ -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
         <property name="prefix" value="/WEB-INF/jsp/"></property>
         <property name="suffix" value=".jsp"></property>
    </bean>
��/WEB-INF/jsp/����index.jsp
���ʱ����http://localhost:8080/spring-mybatis/manualInfo/index.do������ת��indexҳ�棬�������ʾ����Hello World!
���÷���json��ʽ����������@ResponseBodyע��
 <!-- springmvc4.0�����÷���-->
<!-- ����ע��������mvc���ܣ�jsonǰ��̨����-->
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
ManualInfoController�ļ����getObj����
 	 @RequestMapping("/getObj")
	 @ResponseBody
	 public ManualInfo getObj(){
		 ManualInfo m = new ManualInfo() ;
		 m.setDowloadUrl("11111");
		 m.setManualName("");
		 System.out.println("11111");
		return m;
	 }
����·����http://localhost:8080/spring-mybatis/manualInfo/getObj.do
����json��ʽ��{"id":null,"manualName":"","manualDesc":null,"dowloadUrl":"11111","autoEnterpriseCode":null,"updateDate":null,"manualType":null}
---------------���˴�springmvc�����ɴ
==================================================================
---------------spring+mybatis��ʼ�
5.<����mybatis��jar��-->
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
  	
6.<!--�������ݿ����ӳ�jar����mysql������-->
	<!--alibaba ���ݿ����ӳ� start-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.12</version>
        </dependency>
  	<!-- alibaba ���ݿ����ӳ� end -->
  	  <!-- mysql������  start-->  
         <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.0.8</version>
        </dependency>
      <!-- mysql������ end --> 
8.<!--����spring jar�� -->
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
9.����classpath:jdbc.properties�ļ�
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/semi?characterEncoding=UTF-8&serverTimezone=UTC
jdbc.username=root
jdbc.password=123456
jdbc.configLocation=mysql

10.����classpath:spring.xml�ļ�
<context:property-placeholder location="classpath:configs/jdbc.porperties"/>
    
    <!-- dataSource ����-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    	<property name="driverClassName" value="${jdbc.driverClassName}"></property>
		<property name="url" value="${jdbc.url}"></property>
		<property name="username" value="${jdbc.username}"></property>
		<property name="password" value="${jdbc.password}"></property>
		  <!-- ���ó�ʼ����С����С����� -->
        <property name="initialSize" value="10" />
        <property name="minIdle" value="10" />
        <property name="maxActive" value="20" />
 
        <!-- ���û�ȡ���ӵȴ���ʱ��ʱ�� -->
        <property name="maxWait" value="60000" />
 
        <!-- ���ü����òŽ���һ�μ�⣬�����Ҫ�رյĿ������ӣ���λ�Ǻ��� -->
        <property name="timeBetweenEvictionRunsMillis" value="60000" />
 
        <!-- ����һ�������ڳ�����С�����ʱ�䣬��λ�Ǻ��� -->
        <property name="minEvictableIdleTimeMillis" value="300000" />
 
        <property name="validationQuery" value="SELECT 'x'" />
        <property name="testWhileIdle" value="true" />
        <property name="testOnBorrow" value="false" />
        <property name="testOnReturn" value="false" />
 
        <!-- ��PSCache������ָ��ÿ��������PSCache�Ĵ�С -->
        <property name="poolPreparedStatements" value="false" />
        <property name="maxPoolPreparedStatementPerConnectionSize" value="20" />
 
        <!-- ���ü��ͳ�����ص�filters -->
        <property name="filters" value="stat" />
    </bean>
    
      <!-- ��������� -->
	<bean id="transactionmanager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
	</bean>
	
	<!-- ����������� -->
	<tx:advice id="serviceAdvice" transaction-manager="transactionmanager">
		<tx:attributes>
			<tx:method name="save*" propagation="REQUIRED" rollback-for="Throwable"/>
			<tx:method name="update*" propagation="REQUIRED" rollback-for="Throwable"/>
			<tx:method name="del*" propagation="REQUIRED" rollback-for="Throwable"/>
			<tx:method name="*" read-only="true"/>
		</tx:attributes>
	</tx:advice>
	
	<!-- �������� -->
	<aop:config proxy-target-class="true">
		<aop:pointcut id="servicePointcut" expression="execution(* cn.ffcs.tsp.msa.mybatis.service.*.*(..))"/>
		<aop:advisor pointcut-ref="servicePointcut" advice-ref="serviceAdvice"/>
	</aop:config>
11.����classpath:spring-mybatis.xml�ļ�
<!-- ����sqlSessionFactoryBean ��Ҫָ��class-->
    <bean id ="sessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    	<!-- �������ݿ� -->
    	<property name="dataSource" ref="dataSource"/>
    	<!-- ����mybatis�ļ� -->
    	<property name="configLocation" value="classpath:configs/mybatis-config.xml"/>
    	<!-- ����mapper.xml�ļ�  -->
    	<property name="mapperLocations" value="classpath:configs/mappers/${jdbc.configLocation}/*.xml"/>
    	<!-- ���÷��ص�pojoָ���·�� -->
    	<property name="typeAliasesPackage" value="cn.ffcs.tsp.msa.mybatis.entity"/>
    </bean>
     	
     <import resource="classpath:configs/mybatis-config.xml"/>
     <!-- ͨ��ɨ���ģʽ��ɨ��Ŀ¼��com/hoo/mapperĿ¼�£����е�mapper���̳�SqlMapper�ӿڵĽӿڣ� ����һ��bean�Ϳ����� -->  
    <bean  class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="sqlSessionFactoryBeanName" value="sessionFactory"/>
          <property name="basePackage" value="cn.ffcs.tsp.msa.mybatis.mapper"/>
    </bean>
    
12.����mybatis-config.xml�ļ�
<configuration>
	<settings>
	  <!-- ������Ӱ�������ӳ���������õĻ����ȫ�ֿ��ء�Ĭ��ֵtrue -->
	  <setting name="cacheEnabled" value="true"/>
	  <!--�ӳټ��ص�ȫ�ֿ��ء�������ʱ�����й������󶼻��ӳټ��ء� �ض�������ϵ�п�ͨ������fetchType���������Ǹ���Ŀ���״̬��Ĭ��ֵfalse  -->
	  <setting name="lazyLoadingEnabled" value="false"/>
	  <!-- �Ƿ�����һ��䷵�ض���������Ҫ������������ Ĭ��ֵtrue -->
	  <setting name="multipleResultSetsEnabled" value="true"/>
	  <!-- ʹ���б�ǩ������������ͬ���������ⷽ����в�ͬ�ı��֣� ����ɲο���������ĵ���ͨ�����������ֲ�ͬ��ģʽ���۲����������Ľ����Ĭ��ֵtrue -->
	  <setting name="useColumnLabel" value="true"/>
	  <!-- ���� JDBC ֧���Զ�������������Ҫ�������ݡ� �������Ϊ true ���������ǿ��ʹ���Զ���������������һЩ�������ܼ��ݵ��Կ��������������� Derby���� Ĭ��ֵfalse  -->
	  <setting name="useGeneratedKeys" value="false"/>
	  <!--  ָ�� MyBatis Ӧ����Զ�ӳ���е��ֶλ����ԡ� NONE ��ʾȡ���Զ�ӳ�䣻PARTIAL ֻ���Զ�ӳ��û�ж���Ƕ�׽����ӳ��Ľ������ FULL ���Զ�ӳ�����⸴�ӵĽ�����������Ƿ�Ƕ�ף��� --> 
	  <!-- Ĭ��ֵPARTIAL -->
	  <setting name="autoMappingBehavior" value="PARTIAL"/>
	  
	  <!--<setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>  -->
	 <!--  ����Ĭ�ϵ�ִ������SIMPLE ������ͨ��ִ������REUSE ִ����������Ԥ������䣨prepared statements���� BATCH ִ������������䲢ִ���������¡�Ĭ��SIMPLE  -->
	  <setting name="defaultExecutorType" value="SIMPLE"/>
	  <!-- ���ó�ʱʱ�䣬�����������ȴ����ݿ���Ӧ�������� -->
	  <setting name="defaultStatementTimeout" value="25"/>
	  
	  <!--<setting name="defaultFetchSize" value="100"/>  -->
	  <!-- ������Ƕ�������ʹ�÷�ҳ��RowBounds��Ĭ��ֵFalse -->
	  <setting name="safeRowBoundsEnabled" value="false"/>
	  <!-- �Ƿ����Զ��շ���������camel case��ӳ�䣬���Ӿ������ݿ����� A_COLUMN ������ Java ������ aColumn ������ӳ�䡣  Ĭ��false -->
	  <setting name="mapUnderscoreToCamelCase" value="false"/>
	  <!-- MyBatis ���ñ��ػ�����ƣ�Local Cache����ֹѭ�����ã�circular references���ͼ����ظ�Ƕ�ײ�ѯ��
	  		 Ĭ��ֵΪ SESSION����������»Ỻ��һ���Ự��ִ�е����в�ѯ��
	   		������ֵΪ STATEMENT�����ػỰ���������ִ���ϣ�����ͬ SqlSession �Ĳ�ͬ���ý����Ṳ�����ݡ�  -->
	  <setting name="localCacheScope" value="SESSION"/>
	  <!-- ��û��Ϊ�����ṩ�ض��� JDBC ����ʱ��Ϊ��ֵָ�� JDBC ���͡� ĳЩ������Ҫָ���е� JDBC ���ͣ��������ֱ����һ�����ͼ��ɣ����� NULL��VARCHAR �� OTHER��  -->
	  <setting name="jdbcTypeForNull" value="OTHER"/>
	  <!--   ָ���ĸ�����ķ�������һ���ӳټ��ء�  -->
	  <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
	  <!-- ��ӡ��ѯ��� -->
       <setting name="logImpl" value="STDOUT_LOGGING" />
	</settings>
</configuration>

13.����mapper.xml
14.����mapper��Ӧ��mapperInterface.java
15.����mybatis������

�������⣺ͨ�����ƥ���ȫ��, ���޷��ҵ�Ԫ�� 'mvc:annotation-driven'��������
ԭ���ǣ���Ȼ��xml�ļ��Ϸ�������mvc����û�����ô�������Ӧ���ļ���Ϣ����ȷ�������£�
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:context="http://www.springframework.org/schema/context"
xmlns:mvc="http://www.springframework.org/schema/mvc"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd ">--ԭ�����ȱʧ


�������⣺mybatis-config.xml·�����������Ҳ���·��
��������mapper.xml��namespace��mapperInterfaceδ��Ӧ
==================mapperInferce��������TestApplication���Գɹ����ɹ��������ݿ⣬mybatis��ɹ�
======��Ŀ��������@Controller��@Service������Ҫ����@Reposity


������ʽ��1.java��дjetty������������2.tomcat������ʽ
jetty������Ҫ����jettyjar����������Ҫɾ��javax.servlet.api��jar��������javax.servlet.http.HttpSessionIdListener

//��������redis���
���ȵ���spring-data-redishe��redis.client��jar��
redis.client���2.4�汾���ϣ�redis.maxActive��Ҫ��redis.maxTotal���棬maxWait�ĳ�maxWaitMillis
<context:property-placeholder location="classpath:jdbc.porperties"/>���ñ�ǩֻ����һ��xml�б�ʹ�ã�Ŀǰredis��jdbc����������jdbc.properties�С�
�汾�õ�spring-data-redishe 1.6.2��redis.client 2.9.0�������ڼ�һֱ������������汾��Щ��ͻorg.springframework.core.serializer.support.DeserializingConverter.<init>(Ljava/lang/ClassLoader;)V
���spring-redis�����ļ�����Ҫ����poolConfig�����ӳ����ã�jedisConnectionFactory��redis����������Ϣ���ã�redisTemplate���ã��ǵý������л�
���� ����redis��keyΪ������������ʽ"\xac\xed\x00\x05t\x00\x04test"��spring-redis�������
spring-data-redis 1.6.2��֧�ּ�Ⱥ��������Ҫ����1.7.0


//����mongodb
���jar������
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
����spring-mongo.xml
����mongo����Դ
����org.springframework.data.mongodb.core.MongoTemplate ������ɾ�Ĳ����
����hbase
�������jar��