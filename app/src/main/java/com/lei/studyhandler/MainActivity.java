package com.lei.studyhandler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * 子线程发送消息通知主线程
 */
public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Handler handler = new Handler() {
        /**
         * 处理消息（被主线程执行）
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText((String)msg.obj);

            //判断当前函数是否被主线程调用的方式
//            boolean result = Looper.getMainLooper() == Looper.myLooper();
//            Log.d("tag",result + "");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        //启动线程
        Thread th = new MyThread();
        th.start();
    }

    class MyThread extends Thread {
        public void run() {
            //判断当前函数是否被主线程调用的方式
            boolean result = Looper.getMainLooper() == Looper.myLooper();
            Log.d("tag",result + "");
            try {
                Thread.sleep(6000);
                //伪代码来体现
                Log.i("tag","访问网络");
                String str = "我是数据";

                //创建Message对象
                Message msg = new Message();
                msg.obj = str;
                //发送消息
                handler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
