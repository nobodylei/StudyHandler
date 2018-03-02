package com.lei.studyasynctask2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.tv_text);

        AsyncTask task = new MyAsyncTask();

        task.execute();//执行
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings){
            Log.i("tag","doInBackground" + strings[0]);
            for(int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(1000);
                    //推送当前的进度
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        /**
         * publishProgress后执行这个方法
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //更新UI上的进度
            text.setText(values[0] + "");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            text.setText(s);
        }
    }
}
