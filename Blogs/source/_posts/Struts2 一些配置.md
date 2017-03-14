---
title: Struts2 中一些配置
date: 2016-10-29 10:2:3
categories: "Struts2" 
tags: [Struts2]
comments: true
---

# Struts2 中的一些简单的配置 #

### 在web.xml 中: ###

    <filter>
		<filter-name>struts2</filter-name>
		<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>struts2</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

<!--more-->


### 在Struts.xml 中: ###
    <?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE struts PUBLIC
		"-//Apache Software Foundation//DTD Struts Configuration 2.3//EN"
		"http://struts.apache.org/dtds/struts-2.3.dtd">
	<struts>
		<!-- 动态方法调用,一般都是为 false -->
	    <constant name="struts.enable.DynamicMethodInvocation" value="false" />
	    <!-- 是否启用开发者模式 -->
	    <constant name="struts.devMode" value="true" />
	
	    <package name="hehe" namespace="/" extends="struts-default">
			<!--默认的要到的位置  -->
	        <!-- <default-action-ref name="index" /> -->
	        
			
			<!-- 什么异常 对应的 -->
	        <global-exception-mappings>
	            <exception-mapping exception="java.lang.Exception" result="error"/>
	        </global-exception-mappings>
	        
	        <!-- 全局错误页面 -->
	        <global-results>
	            <result name="error">/WEB-INF/jsp/error.jsp</result>
	        </global-results>
	
	      <!--<action name="index">
	            <result type="redirectAction">
	                <param name="actionName">HelloWorld</param>
	                <param name="namespace">/example</param>
	            </result>
	        </action> -->
	        
	    </package>
	
		<include file="student.xml"/>
	</struts>


### Struts中表单的验证 ###

![验证格式图](http://i.imgur.com/HAIFjK5.png)

UserAction-loginSubmit-validation.xml

**UserAction:action 类名;loginSubmit:该类中的方法名;validation:必须这个结尾;**

**如果没有loginSubmit:说明UserAction 中的所用的方法都会验证;**


> 验证的规则我们可以看文档;下载下来的 Struts中就有,下面一些简单的例子;


    <?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE validators PUBLIC
		"-//Apache Struts//XWork Validator 1.0.3//EN"
		"http://struts.apache.org/dtds/xwork-validator-1.0.3.dtd">    
		  
	<validators>
		<!-- 与页面form表单对应的 name 的值 -->
		<field name="uname">
			<field-validator type="requiredstring">
				<param name="trim">true</param>  
				<message>用户名不能为空！</message>
			</field-validator>
			
			<field-validator type="regex">
				<!-- param name值为：regex：正则表达式  -->
	            <param name="regex"><![CDATA[(\w{5,})]]></param>
	            <message>长度要大于5位！</message>
	        </field-validator>    
		</field>
		
		<field name="upwd">
			<field-validator type="requiredstring">
				<param name="trim">true</param>
				<message>密码不能为空！</message>
			</field-validator>
			
			<field-validator type="regex">
				<param name="regex"><![CDATA[(^\w*$)]]></param>
				<message>密码不合理含有非法字符</message>
			</field-validator>
			
			<field-validator type="stringlength">
				<param name="minLength">6</param>
				<param name="maxLength">16</param>
				<message><![CDATA[密码必须在6到16之间]]></message>
			</field-validator>
			
		</field>
		<field name="reupwd">
			<field-validator type="fieldexpression">
	   			<param name="expression"><![CDATA[reupwd==upwd]]></param>
	   			<message>密码不一致！</message>
	     	</field-validator>
		</field>
	       
		<field name="birth">
			<field-validator type="date">
				<param name="min">2015-1-1</param>
				<param name="max">2016-12-12</param>
				<message>日期范围有误！请重新输入!</message>
			</field-validator>
		</field>
	</validators>

**Make sure there is a result for "input" listed in the struts.xml for when the validation fails:**

**一定要在Struts.xml 写入 action中 result 名为:input 的返回页面,不然会报错**
	

> Struts2 中自定义拦截器


1. 写一个类 继承 AbstractInterceptor
2. 在xml 中使用


课参考老师笔记 github.com/s126; notes;

> Struts2 中的国际化,ognl表达式,值栈, 获取值栈中的值;  值的获取... 笔记中;;














	
