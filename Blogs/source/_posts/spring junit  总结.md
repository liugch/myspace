---
title: Spring Test Junit 基本使用
date: 2016-10-28 17:20:10
categories: "Spring" 
tags: [Spring]
comments: true
---

> IDEA Spring Junit 单元测试

参考文档：[http://www.ibm.com/developerworks/cn/java/j-lo-springunitest/](http://www.ibm.com/developerworks/cn/java/j-lo-springunitest/)

<!--more-->
> 一些简单注解
@BeforeClass

**用来执行整个测试类需要一次性初始化的环境 如加载spring中配置文件**

@RunWith 

**注释标签是 Junit 提供的，用来说明此测试类的运行者，这里用了SpringJUnit4ClassRunner，**
**这个类是一个针对 Junit 运行环境的自定义扩展，用来标准化在 Spring 环境中 Junit4.5 的测试用例，**
**例如支持的注释标签的标准化**

@ContextConfiguration 

**注释标签是 Spring test context 提供的，用来指定 Spring 配置信息的来源**

@Transactional 

**注释标签是表明此测试类的事务启用，这样所有的测试方案都会自动的 rollback，即您不用自己清除自己所做的任何对数据库的变更了**

@ActiveProfiles("test") 

**@ActiveProfiles，可以指定一个或者多个 profile，这样我们的测试类就仅仅加载这些名字的 profile 中定义的 bean 实例。**


@Repeat(3)

**通过 @Repeat，您可以轻松的多次执行测试用例，而不用自己写 for 循环**

----------

**对 TestNG 的支持**
Spring 2.5 以后，就开始支持 TestNG 了，支持的方法包括：
将您的 TestNG 测试**类继承 Spring 的测试父类**：
AbstractTransactionalTestNGSpringContextTests 或者 AbstractTestNGSpringContextTests，
这样您的 TestNG 测试类内部就可以访问 applicationContext 成员变量了

**不继承 Spring 父类**，在测试类上使用 
@TestExecutionListeners 注释标签，可以引入的监听器包括
DependencyInjectionTestExecutionListener：使得测试类拥有依赖注入特性
DirtiesContextTestExecutionListener：使得测试类拥有更新 applicationContext 能力
TransactionalTestExecutionListener：使得测试类拥有自动的事务管理能力


----------

> @Autowired注解、@Resource注解的区别  一般都会用 @Resource

**@Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了**
**@Resource有两个属性是比较重要的，分是name和type，Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。**
**所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。**
**如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。**




> hibernate 不同版本的 SchemaExport 区别


 	@Test
    public void ExportDB() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport export = new SchemaExport();
        export.create(EnumSet.of(TargetType.DATABASE), metadata);
     /*   // 默认读取hibernate.cfg.xml文件
        Configuration cfg = new Configuration().configure();

        // 生成并输出sql到文件（当前目录）和数据库
        SchemaExport schemaExport = new SchemaExport(cfg);

        // true 在控制台打印sql语句，true 导入sql语句到数据库，即可执行
        schemaExport.create(true, false);*/

    /*    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

        MetadataImplementor metadataImplementor = (MetadataImplementor)
                new MetadataSources(serviceRegistry).buildMetadata();

        SchemaExport export = new SchemaExport(serviceRegistry, metadataImplementor);
        export.create(true, true);*/
    }
