---
title: Oralce的世界
date: 2016-09-24 17:40:51
categories: "Oracle" 
tags: [Oracle]
comments: true
---

# Oracle 的体系结构

数据库是一个以某种有组织的方式存储的数据集合。 它本质上是用计算机存储记录的系统，是位于用户和系统之间的一层管理软件。 Oracle 数据库是目前最流行、最强大的面向对象的关系型数据库(ORDBMS)。

<!--more-->
 
Oracle 是基于请求响应的方式，分为服务端和客户端。

## 服务端
 
Oracle 是由实例和数据库两部分组成

### **实例 (INSTANCE)**

 又称为oracle数据库引擎，由由内存即SGA(System Global Area,系统全局区)和后台处理进程组成。

### **数据库(DATEBASE)**

 保存在计算机上的数据文件等。可以从物理物件或逻辑组件的角度看待。

#### 物理组件

 1. **控制文件 (Control File)**

		select * from v$controlfile;

  记录数据库的物理结构和其他控制信息，如数据库名称，数据文件、日志文件名称和位置等。多副本保证安全。

 2. **数据文件 (Data File)**

		select name,status,enabled from v$datafile;

 3. ** 日志文件 (联机日志文件/归档日志文件)**

		 select * from v$log;   -- 查看日志

   保存用户的所有操作。一个数据库至少有两个日志组，轮流写入。
   每个日志组至少有一个日志成员，成员之间是镜像关系，这是为了保护数据。
   联机日志组的交换过程叫切换。当一个组写完会自动切换下一组，也可以手动切换：

		alter system switch logfile   -- 切换日志文件

   如果打开归档模式，切换日志的时候会产生归档文件。打开归档模式命令:

		alter database archivelog

 4. **初始化参数文件 (Parameter File)**
 
		位置:
    	$ORA_HOME/admin/<SID>/pfile/init.ora.xxx

#### 逻辑组件
 
-  **表空间**
	
		select * from v$tablespace;--查询所有的表空间
	
	
 1.	最基本的逻辑结构 ，是数据库恢复的最小单位, 容纳着许多数据库实体, 如表、视图、索引、聚簇、回退段和临时段等。
 2. 默认创建的表空间有 SYSTEM/SYSAUX/TEMP/UNDO/USERS
 3. 表空间跟数据文件是 1vN 的关系。一个表空间至少需要一个相应的数据文件
 4. 在实际生产中，不建议用默认表空间，要根据情况自己创建
 5. 创建/删除表空间的语法：

			--表空间创建
			create tablespace xxx
			datafile 'D:\xxx.dbf'
			size 50m
			autoextend on
			next 50m maxsize 20480m
			extent management local;
		
			-- 创建临时表空间
			create temporary tablespace yyy
			tempfile 'D:\yyy.dbf';
			
			-- 删除
			drop tablespace yyy;
 6. 逻辑上说，一个表空间是由一到多个段组成的。
	
- **段 (Segment)**
1. 段是对象在数据库中占用的空间
2. 每个段，对应的是每个对象，有 table/index/view 等
3. 按照存储对象类型的不同，分为数据段、索引段、回滚段和临时段
4. 逻辑上说，段是由区组成的
    
- ** 区 (Extend)**

1. 由连续的数据块组成，由 Oracle 自动分配管理
2. 当段中的空间已满时，会自动扩展新区
  
- **块**
1. 数据块是Oracle服务器所能分配、读取或写入的最小逻辑单位。
2.  Oracle服务器以数据块为单位管理数据文件的存储空间。
3.  正常情况下，块的大小是操作系统块的整数倍，称作标准块。
4.  可以通过参数 db_block_size 控制，默认 8k.

> 启动过程
   
	startup nomount        -- 只启动了实例 (Instance)
	alter database mount   -- 数据库挂载
	alter database open    -- 数据库打开
	shutdown immediate     -- 立刻关闭

# 客户端
## 连接语法
		sqlplus sys/southit as sysdba     -- 管理员登录
		sqlplus / as sysdba               -- 操作系统认证
		sqlplus scott/tiger               -- 普通用户登录
		sqlplus scott/tiger@remotedb      -- 连接远程数据库
 		-- 未连接状态，之后可以用 conn user/pass 语法登录
		sqlplus /nolog                    
		
----------	

# Oracle 网络配置

## 配置途径

- `直接修改 NETWORK/ADMIN 下的配置文件`
	通过 Oracle 的 Net Manager 工具配置
	服务端 (NETWORK/ADMIN/listener.ora)

- `直接修改配置文件或通过图形界面配置。`
	主要配置元素有：
	协议 (TCP/IP)
	连接地址
	端口号 (默认1521，一般不需要修改)
	开启 TNSListener 服务


----------

# 用户与权限
## 用户
用户是用于资源控制和权限管理的一个概念
开始新的项目时，保持良好的习惯： 为项目创建新的用户，指定新的表空间。

> 系统缺省用户

**SYS 用户**

	又叫特权用户，数据库中至高无上的用户。
	它是数据库的系统管理员，负责的是数据的安装、维护、升级、备份、恢复、优化等操作。
	不能用 normal 的身份登录，必须用 SYSDBA/SYSOPER 的身份登录。
	即使其他的用户用 SYSDBA/SYSOPER 的身份登录，实际上登录的也是 SYS。
**SYSTEM 用户**

	数据库的管理员。SYSTEM 用户之下保存着数据库运行的一些基本字典数据。
	它拥有 DBA 的角色。主要负责对数据库中各种对象，各种资源的管理。
**其他用户 (如SCOTT)**

	普通用户，能够执行被授权范围之内的操作。





