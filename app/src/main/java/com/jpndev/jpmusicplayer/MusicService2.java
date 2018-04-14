package com.jpndev.jpmusicplayer;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * Created by Mani-Ceino on 3/25/2018.
 */

public class MusicService2 extends Service {

	MediaPlayer player;
	private static final int NOTIFY_ID=1,ONGOING_NOTIFICATION_ID=10;
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		player=MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
		player.setLooping(true);
		player.start();

		//intent.getFlags();
			Intent notIntent = new Intent(this, HomeCActivity.class);
		notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent pendInt = PendingIntent.getActivity(this, 0,
				notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		Notification.Builder builder = new Notification.Builder(this);

		builder.setContentIntent(pendInt)
				//.set
				.setSmallIcon(R.drawable.ic_play_24dp)
				//.setTicker("Entarooooo")
				.setOngoing(true)
				.setContentTitle("Playing")
  .setContentText("Lalettan");
		Notification not = builder.build();
	startForeground(NOTIFY_ID, not);



		Notification notification = new Notification(R.mipmap.ic_launcher, "Entarooooo",
				System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, HomeCActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

	/*	notification.setLatestEventInfo(this, getText(R.string.notification_title),
				getText(R.string.notification_message), pendingIntent);*/
		startForeground(ONGOING_NOTIFICATION_ID, notification);


		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy() {
		if(player!=null)
		{
			player.reset();
			player.release();
			player=null;
		}
		stopForeground(true);
		super.onDestroy();
	}
}
