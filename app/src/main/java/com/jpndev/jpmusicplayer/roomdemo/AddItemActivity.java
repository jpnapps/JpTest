package com.jpndev.jpmusicplayer.roomdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jpndev.jpmusicplayer.R;

import java.lang.ref.WeakReference;

/**
 * Created by Mani-Ceino on 5/6/2018.
 */

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {

	private TextInputLayout txtInput;
	private TextInputEditText etTitle;
	private TextInputLayout txtInput2;
	private TextInputEditText etContent;


	private ItemDatabase itemDatabase;
	private MItem mItem;
	private boolean update;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);
		itemDatabase = ItemDatabase.getInstance(AddItemActivity.this);
		txtInput = (TextInputLayout) findViewById(R.id.txtInput);
		etTitle = (TextInputEditText) findViewById(R.id.et_title);
		txtInput2 = (TextInputLayout) findViewById(R.id.txtInput2);
		etContent = (TextInputEditText) findViewById(R.id.et_content);
		Button button = findViewById(R.id.but_save);
		if ( (mItem = (MItem) getIntent().getSerializableExtra("note"))!=null ){
			getSupportActionBar().setTitle("Update Note");
			update = true;
			button.setText("Update");
			etTitle.setText(mItem.getName());
			etContent.setText(mItem.getDescription());
		}
		findViewById(R.id.but_save).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.but_save:

				if (update){
					mItem.setDescription(etContent.getText().toString());
					mItem.setName(etTitle.getText().toString());
					itemDatabase.getItemDao().updateNote(mItem);
					setResult(mItem,2);
				}else {
					mItem = new MItem(etContent.getText().toString(), etTitle.getText().toString());
					new InsertTask(AddItemActivity.this,mItem).execute();
				}



				break;
		}
	}
	private void setResult(MItem note, int flag){
		setResult(flag,new Intent().putExtra("note",note));
		finish();
	}
	private class InsertTask  extends AsyncTask<Void,Void,Boolean>{
		MItem mitem;
		private WeakReference<AddItemActivity> activityReference;
		public InsertTask(AddItemActivity addNoteActivity, MItem mItem) {
			activityReference=new WeakReference<>(addNoteActivity);
			 mitem=mItem;
		}

		@Override
		protected Boolean doInBackground(Void... voids) {
			// retrieve auto incremented note id
			long j = activityReference.get().itemDatabase.getItemDao().insertNote(mitem);
			//note.setNote_id(j);
			Log.e("ID ", "doInBackground: "+j );
			return true;
		}

		// onPostExecute runs on main thread
		@Override
		protected void onPostExecute(Boolean bool) {
			if (bool){
				activityReference.get().setResult(mitem,1);
				activityReference.get().finish();
			}
		}
	}
}
