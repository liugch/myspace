---
title: Android Layout
date: 2016-09-25 17:40:51
categories: "Android" 
tags: [Android]
comments: true
---
# 布局的优化 #

> 尽量的使用 include merge 

> 劲量不要嵌套的太多的布局

> 一般不要 RelativeLayout 里面再次嵌套 RelativeLayout

## 布局的一些简化操作 ##
     <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="120dp"
        android:orientation="horizontal">

        <ImageView
            android:layout_width="120dp"
            android:layout_height="120dp"
            android:layout_margin="5dp"
            android:src="@drawable/touxiang" />
        <!--
		ineSpacingExtra属性代表的是行间距，他默认是0，是一个绝对高度值。
		同时还有lineSpacingMultiplier属性， 它代表行间距倍数，默认为1.0f，是一个相对高度值。
		android:text="你好\n第三方\n大都饭店" 自动回帮你换行
		-->
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:gravity="center_vertical"
            android:lineSpacingMultiplier="2"
            android:text="你好\n第三方\n大都饭店"
            android:textSize="14sp" />
    </LinearLayout>



> 定义 一个shape的XML

> 调用 自定义的 shape 设置包含item的分割线

> item中的间隔我可以 放弃 view 和 margin 用 space 使用

**类似的效果图**

![](http://i.imgur.com/bV20OlF.png)

**divider.xml**

    <shape xmlns:android="http://schemas.android.com/apk/res/android">
    <size
        android:width="1dp"
        android:height="1dp"/>
    <solid
        android:color="#e1e1e1"/>
	</shape>

**布局**	

	<!--
    	divider:调用 定义的shape  
		showDividers:分割线显示的位置
		android:drawableLeft="@mipmap/shebei
		Space: 设置 间隔
     -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:divider="@drawable/divider"
        android:showDividers="middle|beginning|end"
        android:orientation="vertical">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="40dp"
            android:background="#ffffff"
            android:drawableLeft="@mipmap/shebei"
            android:drawablePadding="5dp"
            android:drawableRight="@drawable/right"
            android:gravity="center_vertical"
            android:paddingLeft="5dp"
            android:paddingRight="5dp"
            android:text="我的设备" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="40dp"
            android:background="#ffffff"
            android:drawableLeft="@mipmap/shebei"
            android:drawablePadding="5dp"
            android:drawableRight="@drawable/right"
            android:gravity="center_vertical"
            android:paddingLeft="5dp"
            android:paddingRight="5dp"
            android:text="我的设备" />
        <Space
            android:layout_width="match_parent"
            android:layout_height="30dp" />
        <TextView
            android:layout_width="match_parent"
            android:layout_height="40dp"
            android:background="#ffffff"
            android:drawableLeft="@mipmap/shebei"
            android:drawablePadding="5dp"
            android:drawableRight="@drawable/right"
            android:gravity="center_vertical"
            android:paddingLeft="5dp"
            android:paddingRight="5dp"
            android:text="我的设备" />
    </LinearLayout>
    
**TextView 实现超链接**

效果图:

![](http://i.imgur.com/LUa8sx6.png)

	//布局
     <TextView
        android:id="@+id/text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:autoLink="all" />

	// activity
	 text=(TextView) findViewById(R.id.text);
        String texts = "网址：http://www.baidu.com"+"\n";
        texts += "邮箱:88888888@qq.com"+"\n";
        texts += "电话：13888888888" +"\n";
        text.setText(texts);

