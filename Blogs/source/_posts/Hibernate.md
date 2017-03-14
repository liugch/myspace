---
title: Hibernate
date: 2016-11-01 00:13:25
categories: "Hibernate" 
tags: [Hibernate]
comments: true
---
# Hibernate工作原理及为什么要用? #

## 原理： ##

1.通过Configuration().configure();读取并解析hibernate.cfg.xml配置文件
2.由hibernate.cfg.xml中的<mapping resource="com/xx/User.hbm.xml"/>读取并解析映射信息
3.通过config.buildSessionFactory();//创建SessionFactory
4.sessionFactory.openSession();//打开Sesssion
5.session.beginTransaction();//创建事务Transation
6.persistent operate持久化操作
7.session.getTransaction().commit();//提交事务
8.关闭Session
9.关闭SesstionFactory
<!--more-->
## 为什么要用： ##

1. 对JDBC访问数据库的代码做了封装，大大简化了数据访问层繁琐的重复性代码。

2. Hibernate是一个基于JDBC的主流持久化框架，是一个优秀的ORM实现。他很大程度的简化DAO层的    	编码工作

3. hibernate使用Java反射机制，而不是字节码增强程序来实现透明性。

4. hibernate的性能非常好，因为它是个轻量级框架。映射的灵活性很出色。它支持各种关系数据库，	从一对一到多对多的各种复杂关系。
 


> Hibernate是如何延迟加载?


1. Hibernate2延迟加载实现：a)实体对象 b)集合（Collection）

2. Hibernate3 提供了属性的延迟加载功能

当Hibernate在查询数据的时候，数据并没有存在与内存中，当程序真正对数据的操作时，对象才存在与内存中，就实现了延迟加载，他节省了服务器的内存开销，从而提高了服务器的性能。


> Hibernate中怎样实现类之间的关系?(如：一对多、多对多的关系)

类与类之间的关系主要体现在表与表之间的关系进行操作，它们都市对对象进行操作，我们程序中把所有的表与类都映射在一起，它们通过配置文件中的many-to-one、one-to-many、many-to-many、



## 说下Hibernate的缓存机制 ##

1. 内部缓存存在Hibernate中又叫一级缓存，属于应用事物级缓存

2. 二级缓存：

  a) 应用及缓存 

  b) 分布式缓存
  条件：数据不会被第三方修改、数据大小在可接受范围、数据更新频率低、同一数据被系统频繁使用、非 关键数据
  c) 第三方缓存的实现

**一级缓存：session级的缓存也叫事务级的缓存，只缓存实体，生命周期和session一致。不能对其进行管理。不用显示的调用**。

**二级缓存：sessionFactory缓存，也叫进程级的缓存，使用第3方插件实现的，也值缓存实体，生命周期和sessionFactory一致，可以进行管理。**

首先配置第3放插件，我们用的是EHCache，在hibernate.cfg.xml文件中加入

	<property name="hibernate.cache.user_second_level_cache">true</property>

在映射中也要显示的调用
	<cache usage="read-only"/>

二
> 级缓存之查询缓存：对普通属性进行缓存。如果关联的表发生了修改，那么查询缓存的生命周期也结束了。

**在程序中必须手动启用查询缓存：query.setCacheable(true);**


> Hibernate的查询方式     Sql、Criteria,object comptosition

> Hql：


1、 属性查询
2、 参数查询、命名参数查询
3、 关联查询
4、 分页查询
5、 统计函数


##  **如何优化Hibernate？** ##


1.使用双向一对多关联，不使用单向一对多

2.灵活使用单向一对多关联

3.不用一对一，用多对一取代

4.配置对象缓存，不使用集合缓存

5.一对多集合使用Bag,多对多集合使用Set

6.继承类使用显式多态

7.表字段要少，表关联不要怕多，有二级缓存撑腰


> Hibernate有哪几种查询数据的方式

  (1)导航对象图查询

  (2)OID查询

  (3)HQL

  (4)QBC

  (5)本地SQL

## **load()和get()的区别** ##

Session.load/get方法均可以根据指定的实体类和id从数据库读取记录，并返回与之对应的实体对象。

如果未能发现符合条件的记录，get方法返回null，而load方法会抛出一个ObjectNotFoundException。
Load方法可返回实体的代理类实例，而get方法永远直接返回实体类。
load方法可以充分利用内部缓存和二级缓存中的现有数据，
而get方法则仅仅在内部缓存中进行数据查找，如没有发现对应数据，将越过二级缓存，
直接调用SQL完成数据读取。

/*
  *load()方法的执行顺序如下：
  *a)：首先通过id在session缓存中查找对象，如果存在此id的对象，直接将其返回
  *b)：在二级缓存中查找，找到后将 其返回。
  *c)：如果在session缓存和二级缓存中都找不到此对象，则从数据库中加载有此ID的对象
  *因此load()方法并不总是导致SQL语句，只有缓存中无此数据时，才向数据库发送SQL！  
  */

 /*
  *与get()的区别：
  *1：在立即加载对象（当hibernate在从数据库中取得数据组装好一个对象后
  *会立即再从数据库取得数据此对象所关联的对象）时，如果对象存在，
  *load()和get()方法没有区别，都可以取得已初始化的对象;但如果当对
  *象不存在且是立即加载时，使用get()方法则返回null,而使用load()则
  *抛出一个异常。因此使用load()方法时，要确认查询的主键ID一定是存在
  *的，从这一点讲它没有get方便！
  *2：在延迟加载对象(Hibernate从数据库中取得数据组装好一个对象后，
  *不会立即再从数据库取得数据组装此对象所关联的对象，而是等到需要时，
  *都会从数据库取得数据组装此对象关联的对象)时，get()方法仍然使用
  *立即加载的方式发送SQL语句，并得到已初始化的对象，而load()方法则
  *根本不发送SQL语句，它返回一个代理对象，直到这个对象被访问时才被
  *初始化。
  */

get()----不支持LAZY

load()----支持LAZY

总之对于get和load的根本区别，一句话，hibernate对于load方法认为该数据在数据库中一定存在，
可以放心的使用代理来延迟加载，如果在使用过程中发现了问题，只能抛异常；
而对于get方法，hibernate一定要获取到真实的数据，否则返回null。
get方法首先查询session缓存，没有的话查询二级缓存，最后查询数据库；
反而load方法创建时首先查询session缓存，没有就创建代理，实际使用数据时才查询二级缓存和数据库

> 引用链接


[http://sishuok.com/forum/blogPost/list/2936.html](http://sishuok.com/forum/blogPost/list/2936.html)
[http://www.cnblogs.com/javaNewegg/archive/2011/08/28/2156521.html](http://www.cnblogs.com/javaNewegg/archive/2011/08/28/2156521.html)
[http://blog.csdn.net/zhaoshl_368/article/details/6577103](http://blog.csdn.net/zhaoshl_368/article/details/6577103)


## hibernate 主键生成策略 ##


	<id name="id" length="22" >
		<generator class="native"></generator>
	</id>

[http://www.cnblogs.com/xlwmin/articles/2189427.html](http://www.cnblogs.com/xlwmin/articles/2189427.html)

> 注意:   
>     在HQL,您应该使用映射的java类名和属性名@ entity,而不是实际的表名和列名
List<?> result = session.createQuery("from Student").list();


