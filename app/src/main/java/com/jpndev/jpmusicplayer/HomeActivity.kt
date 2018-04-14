package com.jpndev.jpmusicplayer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_home2.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
      //startService(Intent(this,MusicService::class.java))

       // start_service_btn.setOnClickListener(View.OnClickListener {  })
        start_service_btn.setOnClickListener(View.OnClickListener { startService(Intent(this, MusicService2::class.java))  })
        stop_service_btn.setOnClickListener(View.OnClickListener { stopService(Intent(this, MusicService2::class.java))  })
    }
}
