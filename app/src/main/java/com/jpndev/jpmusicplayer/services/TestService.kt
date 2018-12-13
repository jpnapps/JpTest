package com.jpndev.jpmusicplayer.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings


public  class TestService : Service() {



    private var mediaPlayer: MediaPlayer? = null

    //fun TestService(): ??? {}


    override fun onBind(intent: Intent): IBinder? {
        // This service is unbounded
        // So this method is never called.
        return null
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }



    override fun onCreate() {
        super.onCreate()
        // Create MediaPlayer object, to play your song.
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        //mediaPlayer = MediaPlayer.create(applicationContext, R.raw.mysong)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Play song.
        mediaPlayer!!.start()

        return Service.START_STICKY
    }

    // Destroy
    override fun onDestroy() {
        // Release the resources
        mediaPlayer!!.release()
        super.onDestroy()
    }
}