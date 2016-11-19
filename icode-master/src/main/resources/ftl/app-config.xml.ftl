<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx" xmlns:jdbc="http://www.springframework.org/schema/jdbc"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="
     http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
     http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
     http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd
     http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
     http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd">
	
	<bean id="myDataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource"  >
   		 <property name="driverClassName">
             <value>org.logicalcobwebs.proxool.ProxoolDriver</value>
         </property>
         <property name="url">
             <value>proxool.db</value>
         </property>
	</bean>
	
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="myDataSource" />
		<property name="typeAliasesPackage" value="${model.packageName}" /> 
		<property name="plugins">  
	       <list>  
	          <bean class="com.core.code.util.PagingPlugin">
	            <property name="dialect" value="mysql"/>  
	          </bean> 
	        </list>  
	    </property> 
	</bean>

	<bean name="mapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" value="${model.packageName}.persistence" />
		<property name="sqlSessionFactory" ref="sqlSessionFactory"/>
	</bean>

	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="myDataSource" />
	</bean>
	
	<tx:annotation-driven transaction-manager="transactionManager" />

</beans>