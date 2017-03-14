
---
title: Spring 一些基本的注解的使用
date: 2016-10-28 19:40:7
categories: "Spring" 
tags: [Spring]
comments: true
---
> Spring Bean 的一些配置


**id:** Bean唯一标识

**Class:** Bean 对应的Class 类

**lazy-init:**
值为: TRUE / false 

<!--more-->

默认为: false

定义这个Bean是否懒初始化(就是用到这个Bean的时候才初始化)

**singleton:** 
值为: TRUE / false 

默认为: TRUE;

定义这个Bean 是否是单例模式,如果是false的话:每获取该Bean 的时候 都不是同一个Bean实例 ,会重新创建一个;

### autowire: ###
值为:
byName 根据名字装配

byType	根据类型装配

constructor 根据构造器装配

默认:default no ;

定义这个Bean的时候是否自动注入其它的Bean的实例;

比如autowire="byName",
如果该Bean对应类中有其它Bean类型的变量时:并且**该变量的属性名 和 xml中定义的某个Bean的id 相同**
就会把这个Bean自动注入进去

### scope: ###
值为:
singleton(单例)
prototype(重新创建实例) 一般用在Action

request,session,global session 仅用于web中;


### abstract: ###
值为: TRUE / false

默认: false;

定义一个Bean是否是抽象Bean, 表示该Bean 不被实例化;

**一般用于,一些 属性不会经常改变; 经常会被其它的Bean 给继承;**

### parent:  ###
值为: 其它 Bean的id值;

**表示继承对应id的Bean ;**


### depends-on: ###
值为: 其它 Bean的id值;

表示实例化该Bean的时候 , **先实例化和 depends-on值的Bean的实例;**


### init-method: ###
用来定义Bean的初始化方法，它会在Bean组装之后调用。它必须是一个无参数的方法。 

### destroy-method： ###
用来定义Bean的销毁方法，它在BeanFactory关闭时调用。同样，它也必须是一个无参数的方法。它只能应用于singleton Bean。 

### factory-method： ###
定义创建该Bean对象的工厂方法。它用于下面的“factory-bean”，表示这个Bean是通过工厂方法创建。此时，“class”属性失效。 

### factory-bean: ###
定义创建该Bean对象的工厂类。如果使用了“factory-bean”则“class”属性失效。


----------
> 如果有很多的xml 可以使用import 导入
> 在集合其它框架整合的时候. 还有其它方式进行导入


    <import resource="spring-test.xml"/>

> 当使用注解的时候  需要定义如下:


    <context:component-scan base-package="com.liugch">
        <context:exclude-filter type="annotation" expression="com.liugch.entity"/>
    </context:component-scan>

>  当要加载 其它的配置文件的时候 如:properties 文件时


    <context:property-placeholder location="jdbc.properties"/>

----------

>  Spring 中一些常用的注解

@Component

@Repository

@Service

@Controller

上面都是一样的作用: 用来 让一个类让Spring容器来管理 , 相当于生成了一个Bean

@Resource

@Autowired

@Qualifier



上面用注入: 一般用 @Resource 就行了



@Scope("prototype") 

让该类不是使用单例;重新创建新的实例;


	

















 
	
