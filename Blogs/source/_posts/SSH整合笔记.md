---
title: SSH 整合笔记
date: 2016-10-28 19:40:7
categories: "SSH" 
tags: [SSH,Spring,Hibernate,Struts2]
comments: true
---
> SSH笔记

rasphone  连接网络

>Spring 的使用教程


[http://wiki.jikexueyuan.com/project/spring/](http://wiki.jikexueyuan.com/project/spring/) 
----------

<!--more-->

> Hibernate.initialize(autho.getBook()) 显式 lazy对象


----------

> Struts2 几种获取参数的方法 
>
[https://my.oschina.net/u/1398304/blog/215874](https://my.oschina.net/u/1398304/blog/215874)
----------

> SSH 整合的一些思路及案例
> 
[http://blog.csdn.net/zhuanzhe117/article/details/48014545](http://blog.csdn.net/zhuanzhe117/article/details/48014545)
----------

> SpringMVC 的一些配置
> 
[https://segmentfault.com/a/1190000005670764#articleHeader7](https://segmentfault.com/a/1190000005670764#articleHeader7)
----------

> SpringMVC 的一些笔记
> 
[http://www.imooc.com/article/1392](http://www.imooc.com/article/1392)
----------

> maven 中oracle jar包


到达下载后的jar包的位置 执行下面语句

mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar -Dfile=ojdbc6.jar -DgeneratePom=true 

----------

> 大神的博客

[http://blog.csdn.net/fengshizty?viewmode=list](http://blog.csdn.net/fengshizty?viewmode=list)
----------

> 无法访问服务器

1.没有部署到服务器中;
2.配置中访问地址已经出错;

> 防止访问时路径有问题
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">

