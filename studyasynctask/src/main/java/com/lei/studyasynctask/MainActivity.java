package com.lei.studyasynctask;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);

        AsyncTask task = new MyAsyncTask();
        //执行
        task.execute();
    }

    class MyAsyncTask extends AsyncTask<Void, Void, String>{

        /**
         * 被主线程执行，在doInBackground函数前执行
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //弹加载对话框
            Log.d("tag","onPreExecute");
        }

        /**
         * 被子线程执行，用来做耗时操作
         * @param voids
         * @return
         */
        @Override
        protected String doInBackground(Void... voids) {
            try {
                //模拟请求网络
                Thread.sleep(4000);
                return "网络数据";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 被主线程执行，在doInBackground后执行
         * @param aVoid
         */
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            //更新数据UI
            tv.setText(aVoid);
        }
    }
}
