---
title: Android WebView 的使用 
date: 2016-10-25 19:45:30
categories: "Android" 
tags: [Android]
comments: true
---
# WebView 基本使用 #

> WebView控件之WebSettings各种配置方法

> http://teachcourse.cn/android-webview-websettings

<!--more-->

**设置WebView要显示的网页：**

互联网用：webView.loadUrl("http://www.google.com"); 

本地文件用：webView.loadUrl("file:///android_asset/XX.html");  

本地文件存放在：assets文件中

**设置WebView是否支持Javascript**

如果访问的页面中有JavaScript，则webview必须设置支持Javascript
webview.getSettings().setJavaScriptEnabled(true);  

----------

> android 各种所需的权限


> http://blog.csdn.net/sweetburden2011/article/details/9167353


