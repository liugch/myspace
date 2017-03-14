---
title: Android 第三方SDK 遇到的坑
date: 2016-09-27 17:40:51
categories: "Android" 
tags: [Android,SDK]
comments: true
---
## 微博中．．． ##

> 异常

 AndroidStudio-/vendor/lib, /system/lib]]] couldn't find "libweibosdkcore.so"

> 成功的解决方案

<!--more-->

导入 下载下来SDK中的 weiboSDKCore_3.1.4.jar 和 libs 下的含有**.so的文件夹**放到libs中,

出现上面的错误,就在和 **main同一级下新建 一个文件夹下,把含有.so的文件夹放入其中**;

然后app 下的 H:\projects\weibo\app\build.gradle 文件中添加如下配置:

最后 **一定要 Sync 一下**;
    

	task nativeLibsToJar(type: Zip, description: "create a jar archive of the native libs") {
	    destinationDir file("$projectDir/libs")
	    baseName "Native_Libs2"
	    extension "jar"
	    from fileTree(dir: "libs", include: "**/*.so")
	    into "lib"
	}
	
	tasks.withType(JavaCompile) {
	    compileTask -> compileTask.dependsOn(nativeLibsToJar)
	}

> 放入的文件的结构图如下

![结构图](http://i.imgur.com/EMXX6Fw.png)

