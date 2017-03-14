---
title: Android 文件地址与缓存
date: 2016-11-1 19:45:2
categories: "Android" 
tags: [Android]
comments: true
---
# 文件地址与缓存 #

> getCacheDir()、getFilesDir()、getExternalFilesDir()、getExternalCacheDir()

>  低版本时所需要的权限 在AndroidManifest文件中加入sdcard操作权限

<!--more-->

    <uses-permission Android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    
    <!--在SDCard中创建与删除文件权限 -->
    <uses-permissioandroid:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/> 
    <!--往SDCard写入数据权限 --> 
    　　 <uses-permissionandroid:name="android.permission.WRITE_EXTERNAL_STORAGE"/> 


* 2 确认sdcard的存在
　　* android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)
* 3 获取扩展存储设备的文件目录
　　* android.os.Environment.getExternalStorageDirectory();


**本机**

getCacheDir():方法用于获取/data/data/<application package>/cache目录

getFilesDir():方法用于获取/data/data/<application package>/files目录


**SD开**

Context.getExternalFilesDir():方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据

**context.getExternalFilesDir(null)::/storage/emulated/0/Android/data/com.main.app.zxingdemo/files**

**getExternalFilesDir().getAbsolutePath:: /storage/emulated/0/Android/data/com.main.app.zxingdemo/files**

Context.getExternalCacheDir():方法可以获取到 SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据

如果使用上面的方法，当你的应用在被用户卸载后，SDCard/Android/data/你的应用的包名/ 这个目录下的所有文件都会被删除，不会留下垃圾信息。

而且上面二个目录分别对应->应用详情里面的”清除数据“与”清除缓存“选项


> 一个获取缓存路劲的方法

    /**
     * 一个方法来获取缓存地址
     * @param context
     * @return
     */
    public String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

> 获取文件保存路径

    /**
     *  获取文件保存的路劲
     * @param context
     * @return
     */
    private String getFileRoot(Context context) {
        //判断sd卡是否存在也就是是否正常挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = context.getExternalFilesDir(null);
            if (external != null) {
				// /storage/emulated/0/Android/data/com.main.app.zxingdemo/files
                return external.getAbsolutePath();
            }
        }
		///data/data/com.main.app.zxingdemo/files
        return context.getFilesDir().getAbsolutePath();
    }



> 其它文件路径

     	Environment.getDataDirectory();
        Environment.getDownloadCacheDirectory();
        Environment.getExternalStorageDirectory();
        Environment.getExternalStoragePublicDirectory("test");
        Environment.getRootDirectory();
        getPackageCodePath();
        getPackageResourcePath();
        getCacheDir() ;
        getDatabasePath("test") ;
        getDir("test", Context.MODE_PRIVATE) ;
        getExternalCacheDir() ;
        getExternalFilesDir("test");
        getExternalFilesDir(null);
        getFilesDir();


**执行后结果为:**

![](http://i.imgur.com/agjdjCS.png)


    



