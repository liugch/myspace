---
title: Android BraodCastReceiver 基本使用
date: 2016-09-28 19:40:51
categories: "Android" 
tags: [Android]
comments: true
---

BroadCastReceiver（广播接受者） 
用于接收广播，一个广播可以被多个广播接受者所接收
自身并不实现图形用户界面，当它收到某个通知后
BroadCastReceiver 可以启动Activity 作为相应，
或者通过NotificationManager 提醒用户，或者启动Service 等等

<!--more-->

> 常见的系统广播

Android 系统在运行的过程中，会产生很多事件， 比如开机，电量改变，收发短信，拨打电话，屏幕锁屏

> BroadCastReceiver 配置方式


静态注册：不够灵活，程序退出后还是可以 接收广播
第一步，写一个写继承BroadCastReceiver
第二步，在Androidmanifest 清单文件中配置
拨打电话广播:
	
    /**
	 * Created by Administrator on 2017/1/3.
	 * 接收打电话的广播接受者
	 */

	public class CallReceiver extends BroadcastReceiver {
	    // 接收信息
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        // 获取电话号码
	        String number = getResultData();
	        Log.i(TAG, "onReceive:"+number);
	        // 改变电话号码
	        setResultData("10086");
	        String number2 = getResultData();
	        Log.i(TAG, "onReceive:"+number2);
	    }
	}

	<!--电话权限-->
   	 <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS"></uses-permission>
  	 <!--电话广播 -->
    <receiver android:name=".Receiver.CallReceiver">
        <intent-filter>
            <action android:name="android.intent.action.NEW_OUTGOING_CALL"></action>
        </intent-filter>
    </receiver>


		
动态注册: 更加的灵活
在程序中使用registerReceiver注册。
使用unregiesterReciver()方法取消注册。



> 一个短息拦截 小案例


> 参考 [http://blog.csdn.net/qq_26787115/article/details/51113053](http://blog.csdn.net/qq_26787115/article/details/51113053)

  	/**
	 * Created by Administrator on 2017/1/4.
	 * 接收 短信的广播接收者
	 *
	 * 短信拦截器
	 */
	public class SMSReceiver extends BroadcastReceiver {

    private static final String TAG ="SMSReceiver" ;
	    @RequiresApi(api = Build.VERSION_CODES.M)
	    @Override
	    public void onReceive(Context context, Intent intent) {
	
	        SmsMessage[] messages= SMSReceiver.getMessagesFromIntent(intent);
	        for (SmsMessage message : messages) {
	            //获取发件人号码
	            String toPhone = message.getOriginatingAddress();
	            //获取短信内容
	            String smsContent = message.getMessageBody();
	            Log.i("SMSReceiver", "发件人号码:" + toPhone + "短信内容" + smsContent);
	
	            //判断是否是拦截的号码
	            if (toPhone.equals("12345678910")) {
	                //拦截广播
	                abortBroadcast();
	            }
	        }
	    }
	
	    /**
	     *  通过意图获取所有的短信信息
	     * @param intent
	     * @return
	     */
	    @RequiresApi(api = Build.VERSION_CODES.M)
	    public static SmsMessage[] getMessagesFromIntent(Intent intent) {

	        Log.i(TAG, "onReceive: " + "收到短信");
	        // 获取短信内容
	        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
	        String format = intent.getStringExtra("format");
	        int pduCount = messages.length;
	        SmsMessage[] msgs = new SmsMessage[pduCount];
	
	        for (int i = 0; i < pduCount; i++) {
	            byte[] pdu = (byte[]) messages[i];
	            //把数组元素转换成短信对象
	            msgs[i] = SmsMessage.createFromPdu(pdu, format);
	        }
	        return msgs;
	    }

	}
	
 	<!--短信权限-->
    <uses-permission android:name="android.permission.RECEIVE_SMS" />
	
	<!--注册短信广播-->
    <receiver android:name=".Receiver.SMSReceiver">
        <intent-filter>
            <!--定义接收的广播,被Google隐藏的权限操作-->
            <action android:name="android.provider.Telephony.SMS_RECEIVED" />
        </intent-filter>
    </receiver>


这样我们运行之下，你会发现，虽然走到拦截这一步，但是并没有阻止显示在短信收件箱里，这里，我们要注意一个优势，就是广播接收者是有优先级定义的，我们只需要在**清单注册根节点**的intent-filter标签里定义一个

> android:priority="1000"

	




