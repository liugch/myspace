---
title: hexo博客搭建
date: 2016-09-25 02:22:53
categories: "hexo" 
tags: [hexo,github]
comments: true
---
# 1.Hexo简介
Hexo是一款基于Node.js 的静态博客框架，并且使用Markdown来解析文章的，本地安装Hexo后，可以利用git版本控制工具来操作Hexo,利用Hexo的主题来自动生成静态的网页。<属于个人的理解>

<!--more-->

查考：[Hexo的帮助文档重要 ](https://hexo.io/zh-cn/docs/ "Hexo帮助文档")


# 2.博客环境的搭建

## 一、安装Git
> 下载地址  [https://git-scm.com/download](https://git-scm.com/download "Git下载地址")

## 二、安装Node.js
> 下载地址  [http://nodejs.cn/download/](http://nodejs.cn/download/ "Node.js下载地址")

## 三、安装Hexo
Git和Node.js 安装好后，在一个合适的位置新建一个文件夹（英文的比较好），之后就开始使用Hexo了。


> Hexo的安装并初始化

		$ npm install -g hexo-cli
		$ hexo init
安装Hexo后会在该文件夹下生成如下的文件

		├── _config.yml
		├── package.json
		├── scaffolds
		├── source
		|   ├── _drafts
		|   └── _posts
		└── themes

其中_config.yml文件用于存放网站的配置信息，你可以在此配置大部分的参数；scaffolds是存放模板的文件夹，当新建文章时，Hexo 会根据scaffold来建立文件；source是资源文件夹，用于存放用户资源，themes是主题文件夹，存放博客主题，Hexo 会根据主题来生成静态页面。

> 生成静态的博文

在Git Bash终端执行命令：

		1  hexo g
		2  hexo s

可以组合起来使用：

		hexo s -g

执行完之后：

Hexo将source文件夹中的 Markdown 和 HTML 文件会被解析并放到public文件夹中，public文件夹用于存放静态博客文件，相当于网站根目录。

> 至此简单的博客已经搭建完毕  访问：http://localhost:4000/


----------

		

