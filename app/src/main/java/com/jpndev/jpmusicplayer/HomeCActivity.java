package com.jpndev.jpmusicplayer;

import android.Manifest;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;

import com.jpndev.jpmusicplayer.model.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.jpndev.jpmusicplayer.Utility.MY_PERMISSIONS_REQUEST_CAMERA;
import static com.jpndev.jpmusicplayer.Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;

/**
 * Created by Mani-Ceino on 3/24/2018.
 */

public class HomeCActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {

	private ArrayList<Song> songList;
	private ListView songView;


	private MusicService musicSrv;
	private Intent playIntent;
	private boolean musicBound=false;


	private MusicController controller;

	private boolean paused=false, playbackPaused=false;
/*	@Override
	public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
		super.onCreate(savedInstanceState, persistentState);

		songList = new ArrayList<Song>();
		songView = (ListView)findViewById(R.id.song_list);
		getSongList();
		setController();
	}*/

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		songList = new ArrayList<Song>();
		songView = (ListView)findViewById(R.id.song_list);

		getSongList();
		setController();
	}

	//connect to the service
	private ServiceConnection musicConnection = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			MusicService.MusicBinder binder = (MusicService.MusicBinder)service;
			//get service
			musicSrv = binder.getService();
			//pass list
			musicSrv.setList(songList);
			musicBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			musicBound = false;
		}
	};

	@Override
	protected void onStart() {
		super.onStart();
		if(playIntent==null){
			playIntent = new Intent(this, MusicService.class);
			bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
			startService(playIntent);

		}
	}

	@Override
	protected void onPause(){
		super.onPause();
		paused=true;
		playbackPaused=true;
		if(musicSrv!=null)
		musicSrv.pausePlayer();
	}
	@Override
	protected void onResume(){
		super.onResume();
		if(paused){
			setController();
			paused=false;
		}
	}
	@Override
	protected void onStop() {
		if(controller!=null)
		controller.hide();
		super.onStop();
	}
	private void setController(){
		//set the controller up
		controller = new MusicController(this);
		controller.setMediaPlayer(this);
		controller.setAnchorView(findViewById(R.id.song_list));
		controller.setEnabled(true);

		controller.setPrevNextListeners(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playNext();
			}
		}, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playPrev();
			}
		});
	}

	//play next
	private void playNext(){
		if(musicSrv!=null)
		musicSrv.playNext();
		if(playbackPaused){
			setController();
			playbackPaused=false;
		}
		controller.show(0);
	}
	//play previous
	private void playPrev(){
		if(musicSrv!=null)
		musicSrv.playPrev();
		if(playbackPaused){
			setController();
			playbackPaused=false;
		}
		controller.show(0);
	}
	public void getSongList() {
		boolean checkPermission =
				Utility.checkPermission(HomeCActivity.this, "gallery");
		if(checkPermission) {


		ContentResolver musicResolver = getContentResolver();
		Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
		if(musicCursor!=null && musicCursor.moveToFirst()) {
			//get columns
			int titleColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
			int idColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
			int artistColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);
			//add songs to list
			do {
				long thisId = musicCursor.getLong(idColumn);
				String thisTitle = musicCursor.getString(titleColumn);
				String thisArtist = musicCursor.getString(artistColumn);
				songList.add(new Song(thisId, thisTitle, thisArtist));
			} while (musicCursor.moveToNext());


			Collections.sort(songList, new Comparator<Song>() {
				public int compare(Song a, Song b) {
					return a.getTitle().compareTo(b.getTitle());
				}
			});

			SongAdapter songAdt = new SongAdapter(this, songList);
			songView.setAdapter(songAdt);
		}

		}

	}

	public void songPicked(View view){
		musicSrv.setSong(Integer.parseInt(view.getTag().toString()));
		musicSrv.playSong();
		if(playbackPaused){
			setController();
			playbackPaused=false;
		}
		controller.show(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//menu item selected

		switch (item.getItemId()) {
			case R.id.action_shuffle:
				//shuffle
				musicSrv.setShuffle();
				break;
			case R.id.action_end:
				stopService(playIntent);
				musicSrv=null;
				System.exit(0);
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		stopService(playIntent);
		musicSrv=null;
		super.onDestroy();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode==MY_PERMISSIONS_REQUEST_CAMERA)
		{
			if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
				getSongList();

			}
			else
			{
				Toast.makeText(this, "Camera permission Denied !", Toast.LENGTH_SHORT).show();
			}
		}
		else if (requestCode==MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
		{
			if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
			{
				getSongList();
			}
			else {
				Toast.makeText(this, "Gallery permission Denied !", Toast.LENGTH_SHORT).show();
			}

		}
	}

	@Override
	public void start() {
		musicSrv.go();
	}

	@Override
	public void pause() {
		musicSrv.pausePlayer();
	}

	@Override
	public int getDuration() {
		if(musicSrv!=null && musicBound && musicSrv.isPng())
			return musicSrv.getDur();
		else return 0;
	}

	@Override
	public int getCurrentPosition() {
		if(musicSrv!=null && musicBound && musicSrv.isPng())
		return musicSrv.getPosn();
          else return 0;
	}

	@Override
	public void seekTo(int pos) {
		musicSrv.seek(pos);
	}

	@Override
	public boolean isPlaying() {
		if(musicSrv!=null && musicBound)
		return musicSrv.isPng();
		return false;
	}

	@Override
	public int getBufferPercentage() {
		return 0;
	}

	@Override
	public boolean canPause() {
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		return true;
	}

	@Override
	public boolean canSeekForward() {
		return true;
	}

	@Override
	public int getAudioSessionId() {
		return 0;
	}
}
