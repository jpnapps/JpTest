package com.jpndev.jpmusicplayer;


import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jpndev.jpmusicplayer.model.Song;

import java.util.ArrayList;
import java.util.Random;
import android.app.Notification;
import android.app.PendingIntent;

/**
 * Created by Mani-Ceino on 3/24/2018.
 */

public class MusicService extends Service  implements
		MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
		MediaPlayer.OnCompletionListener {

	//media player
	private MediaPlayer player;
	//song list
	private ArrayList<Song> songs;
	//current position
	private int songPosn;



	private String songTitle="";
	private static final int NOTIFY_ID=1;
	private boolean shuffle=false;
	private Random rand;


	private final IBinder musicBind = new MusicBinder();
	@Override
	public void onCreate() {
		super.onCreate();

		//initialize position
		songPosn=0;
//create player
		player = new MediaPlayer();
		rand=new Random();
		initMusicPlayer();
	}


	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return musicBind;
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(player==null)
		{
			songPosn=0;
//create player
			player = new MediaPlayer();
			rand=new Random();
			initMusicPlayer();
		}
		return START_STICKY ;
	}

	public void initMusicPlayer(){
		//set player properties
		player.setWakeMode(getApplicationContext(),
				PowerManager.PARTIAL_WAKE_LOCK);
		player.setAudioStreamType(AudioManager.STREAM_MUSIC);

		player.setOnPreparedListener(this);
		player.setOnCompletionListener(this);
		player.setOnErrorListener(this);
	}

	public void setList(ArrayList<Song> theSongs){
		songs=theSongs;
	}
	public void setSong(int songIndex){
		songPosn=songIndex;
	}
	public void setShuffle(){
		if(shuffle) shuffle=false;
		else shuffle=true;
	}
	public class MusicBinder extends Binder {
		MusicService getService() {
			return MusicService.this;
		}
	}


	public void playSong(){
		//play a song

		player.reset();

		//get song
		Song playSong = songs.get(songPosn);
		songTitle=playSong.getTitle();
//get id
		long currSong = playSong.getID();
//set uri
		Uri trackUri = ContentUris.withAppendedId(
				android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				currSong);
		try{
			player.setDataSource(getApplicationContext(), trackUri);
		}
		catch(Exception e){
			Log.e("MUSIC SERVICE", "Error setting data source", e);
		}

		player.prepareAsync();
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
	/*	if(mp.getCurrentPosition()!=0){
			mp.reset();
			playNext();
		}*/

		mp.reset();
		playNext();
	}

	@Override
	public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
		mediaPlayer.reset();
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mediaPlayer) {
		//start playback
		mediaPlayer.start();

	/*	Intent notIntent = new Intent(this, HomeCActivity.class);
		notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent pendInt = PendingIntent.getActivity(this, 0,
				notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		Notification.Builder builder = new Notification.Builder(this);

		builder.setContentIntent(pendInt)
				//.set
				.setSmallIcon(R.drawable.ic_play_24dp)
				.setTicker(songTitle)
				.setOngoing(true)
				.setContentTitle("Playing")
  .setContentText(songTitle);
		Notification not = builder.build();
*/
		//startForeground(NOTIFY_ID, not);

	}

	@Override
	public boolean onUnbind(Intent intent){
		if(player!=null) {
			player.stop();
			player.release();
		}
		return false;
	}


	@Override
	public void onDestroy() {
		stopForeground(true);
	}
/** Control methods */

	public int getPosn(){
		return player.getCurrentPosition();
	}

	public int getDur(){
		return player.getDuration();
	}

	public boolean isPng(){
		return player.isPlaying();
	}

	public void pausePlayer(){
		player.pause();
	}

	public void seek(int posn){
		player.seekTo(posn);
	}

	public void go(){
		player.start();
	}

	public void playPrev(){
		songPosn--;
		if(songPosn<0) songPosn=songs.size()-1;
		playSong();
	}

	//skip to next
	public void playNext(){
		/*songPosn++;
		if(songPosn==(songs.size()-1) songPosn=0;
		playSong();*/

		if(shuffle){
			int newSong = songPosn;
			while(newSong==songPosn){
				newSong=rand.nextInt(songs.size());
			}
			songPosn=newSong;
		}
		else{
			songPosn++;
			if(songPosn==(songs.size()-1)) songPosn=0;
		}
		playSong();
	}
}
