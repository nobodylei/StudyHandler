package com.lei.ghostthreadstudy;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private boolean flag = false;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MyThread().start();

        runnable = new Runnable() {
            @Override//被主线程调用，可用来更新UI
            public void run() {
                boolean result = Looper.getMainLooper() == Looper.myLooper();
                Log.i("tag", "runnable" + result);
                System.out.println("ddd");

            }
        };
    }

    class MyThread extends Thread {
        public void run() {//模拟打开网络图片
            handler.post(runnable);
            for (int i = 0; i <= 100; i++) {
                if (flag) {
                    break;
                }
                try {
                    Thread.sleep(1000);
                    Log.i("tag", "打开" + i + "%");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        flag = true;
    }
}
