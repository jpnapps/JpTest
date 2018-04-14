package com.jpndev.jpmusicplayer.asyntask;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jpndev.jpmusicplayer.HomeCActivity;
import com.jpndev.jpmusicplayer.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mani-Ceino on 4/1/2018.
 */

public class AsynTaskHeadlessFragment extends Fragment {


	private ProgressDialog progressDialog;
	private boolean isTaskRunning = false;


	FragmentClick xFragmentClick;
	public interface FragmentClick
	{
		public void onSetProgressText(String color);
		public void onSetProgressText2(String color);
		public void onSetProgress(int color);
		public void onSetProgress2(int color);
		public void onSetProgressVisible();
		public void onSetProgress2Visible();
	}






	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			xFragmentClick = (FragmentClick) activity;
		} catch (Exception e) {

		}
	}



	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		task = new DownloadWebPageTask();
		task.execute(new String[] { "http://www.vogella.com/index.html"});
		return null;
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// If we are returning here from a screen orientation
		// and the AsyncTask is still working, re-create and display the
		// progress dialog.
		if (isTaskRunning) {
			progressDialog = ProgressDialog.show(getActivity(), "Loading", "Please wait a moment!");
		}
	}

	DownloadWebPageTask task;







	private class DownloadWebPageTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//      progressTv.setText("Cleared");
			xFragmentClick.onSetProgressVisible();
			xFragmentClick.onSetProgress(0);
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
              */
			xFragmentClick.onSetProgress(100);
			xFragmentClick.onSetProgressText(result);
			lockScreenOrientation();
			unlockScreenOrientation();
			onTaskFinished("completed");
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

			xFragmentClick.onSetProgress(values[0]);
			xFragmentClick.onSetProgressVisible();

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
			xFragmentClick.onSetProgress2Visible();
			xFragmentClick.onSetProgress2(0);
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
              */
			xFragmentClick.onSetProgress2(100);
			xFragmentClick.onSetProgressText2(result);
			lockScreenOrientation();
			unlockScreenOrientation();
			onTaskFinished("completed");
           /* }catch (Exception e)
            {
                e.printStackTrace();
            }*/
			//textView.setText(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// super.onProgressUpdate(values);

			xFragmentClick.onSetProgress(values[0]);
			xFragmentClick.onSetProgressVisible();

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
		progressDialog = ProgressDialog.show(getActivity(), "Loading", "Please wait a moment!");
		isTaskRunning = true;
	}

	/*   @Override*/
	public void onTaskFinished(String result) {
/*		if (progressDialog != null) {
			progressDialog.dismiss();
		}*/

		isTaskRunning = false;
		progressDialog.dismiss();
		onDetach();


	}

	@Override
	public void onDetach() {
		// All dialogs should be closed before leaving the activity in order to avoid
		// the: Activity has leaked window com.android.internal.policy... exception
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
		super.onDetach();
	}
}


