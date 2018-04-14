package com.jpndev.jpmusicplayer.asyntask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jpndev.jpmusicplayer.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mani-Ceino on 4/1/2018.
 */

public class AsynTaskHeadlessActivity extends AppCompatActivity implements View.OnClickListener,AsynTaskHeadlessFragment.FragmentClick{

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

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.asyntask_tv:
				// Create new fragment and transaction
				Fragment newFragment = new AsynTaskHeadlessFragment();
				FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
				//transaction.replace(R.id.fragment_container, newFragment);
				transaction.add(newFragment,"dd");
				transaction.addToBackStack(null);

// Commit the transaction
				transaction.commit();
				break;
			case R.id.home_tv:

				Intent intent=new Intent(AsynTaskHeadlessActivity.this, AsyntaskActivityWIthFragment.class);
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


	@Override
	public void onSetProgressText(String progress) {
		progressTv.setText(Html.fromHtml(progress));
	}

	@Override
	public void onSetProgressText2(String progress) {
		progress2_tv.setText(Html.fromHtml(progress));
	}

	@Override
	public void onSetProgress(int progress) {
		progressBar.setProgress(progress);
	}

	@Override
	public void onSetProgress2(int progress) {
		progressBar2.setProgress(progress);
	}

	@Override
	public void onSetProgressVisible() {
		progressBar.setVisibility(View.VISIBLE);

	}

	@Override
	public void onSetProgress2Visible() {
		progressBar2.setVisibility(View.VISIBLE);

	}
}

