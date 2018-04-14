package com.jpndev.jpmusicplayer.asyntask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jpndev.jpmusicplayer.HomeCActivity;
import com.jpndev.jpmusicplayer.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;

public class ActivityAsyntaskActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout topLlay;
    private TextView asyntaskTv;
    private TextView progressTv,progress2_tv;
    private ProgressBar progressBar,progressBar2;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyntask);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        topLlay = (LinearLayout) findViewById(R.id.top_llay);
        asyntaskTv = (TextView) findViewById(R.id.asyntask_tv);
        progressTv = (TextView) findViewById(R.id.progress_tv);
        progress2_tv = (TextView) findViewById(R.id.progress2_tv);
        //findViewById(R.id.home_tv)
    }
    DownloadWebPageTask task;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.asyntask_tv:
                 task = new DownloadWebPageTask();
                task.execute(new String[] { "http://www.vogella.com/index.html"});
                DownloadWebPageTask2 task2 = new DownloadWebPageTask2();
                task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,new String[] { "https://developer.android.com/reference/android/os/AsyncTask.html"});


           /*    String[] urls=new String[]{"http://www.vogella.com/index.html","https://developer.android.com/reference/android/os/AsyncTask.html"};
               for(int i=0;i<urls.length;i++)
               {
                   DownloadWebPageTask task = new DownloadWebPageTask();
                 task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,new String[] { urls[i]});
                  // task.execute(new String[] { urls[i]});
               }*/
                break;
            case R.id.home_tv:

                Intent intent=new Intent(ActivityAsyntaskActivity.this, AsyntaskActivityWIthFragment.class);
                startActivity(intent);
              //  finish();
                break;

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("progress2_tv",""+progress2_tv.getText());
        outState.putString("progressTv",""+progressTv.getText());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        outState.putString("progress2_tv",""+progress2_tv.getText());
        outState.putString("progressTv",""+progressTv.getText());

        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null) {
            String progress2 = savedInstanceState.getString("progress2_tv", "");
            String progress = savedInstanceState.getString("progressTv", "");
            progress2_tv.setText(progress2);
            progressTv.setText(progress);
        }

    }


    private class DownloadWebPageTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
      //      progressTv.setText("Cleared");
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            lockScreenOrientation();
            onTaskStarted();
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                // we use the OkHttp library from https://github.com/square/okhttp
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(urls[0]).build();
                Response response = client.newCall(request).execute();
                publishProgress(10);
                doFakeWork(4000);
                publishProgress(60);
                doFakeWork(3000);
                publishProgress(80);
                doFakeWork(2000);
                publishProgress(90);
                if(response.isSuccessful()) {
                    return response.body().string();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
                return "Download failed exception "+e.getMessage();
            }

            return "Download failed";
        }

        @Override
        protected void onPostExecute(String result) {
          /*  try
            {
              */  progressTv.setText(Html.fromHtml(result));
            progressTv.setMaxLines(5);
            lockScreenOrientation();
            unlockScreenOrientation();
           // onTaskFinished("completed");
          //  onPreExecute();

           /* }catch (Exception e)
            {
                e.printStackTrace();
            }*/
            //textView.setText(result);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // super.onProgressUpdate(values);
          //  progressTv.setText("percentage "+values[0]);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(values[0]);


        }

        // Simulating something timeconsuming
        private void doFakeWork(int time) {
            SystemClock.sleep(time);
        }
    }


    private class DownloadWebPageTask2 extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //      progressTv.setText("Cleared");
            progressBar2.setVisibility(View.VISIBLE);
            progressBar2.setProgress(0);
            lockScreenOrientation();
           // onPostExecute("checking");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                // we use the OkHttp library from https://github.com/square/okhttp
              // Toast.makeText(getApplicationContext()," toast ",Toast.LENGTH_SHORT).show();

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(urls[0]).build();
                Response response = client.newCall(request).execute();
                publishProgress(10);
                doFakeWork(2000);
                publishProgress(40);
                doFakeWork(3000);
                publishProgress(70);
                doFakeWork(3000);
                publishProgress(90);
                if(response.isSuccessful()) {
                    return response.body().string();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
                return "Download failed exception "+e.getMessage();
            }

            return "Download failed";
        }

        @Override
        protected void onPostExecute(String result) {
          /*  try
            {
              */  progress2_tv.setText(Html.fromHtml(result));

            progress2_tv.setMaxLines(5);
            lockScreenOrientation();
            unlockScreenOrientation();

           /* }catch (Exception e)
            {
                e.printStackTrace();
            }*/
            //textView.setText(result);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // super.onProgressUpdate(values);
            //  progressTv.setText("percentage "+values[0]);
            progressBar2.setVisibility(View.VISIBLE);
            progressBar2.setProgress(values[0]);

        }

        // Simulating something timeconsuming
        private void doFakeWork(int time) {
            SystemClock.sleep(time);
        }
    }


    private void lockScreenOrientation() {
      /*  int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }*/
    }

    private void unlockScreenOrientation() {
       /* setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);*/
    }


/*    @Override*/
    public void onTaskStarted() {
        progressDialog = ProgressDialog.show(ActivityAsyntaskActivity.this, "Loading", "Please wait a moment!");
    }

 /*   @Override*/
    public void onTaskFinished(String result) {
 progressDialog.dismiss();

       /* if (progressDialog != null) {
            progressDialog.dismiss();
        }*/
    }
}
