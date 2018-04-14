package com.jpndev.jpmusicplayer.launchmode

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.jpndev.jpmusicplayer.MusicService2
import com.jpndev.jpmusicplayer.R

import kotlinx.android.synthetic.main.activity_kotline_u.*

class KotlineActivityU : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotline_u)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, KotlineActivityV::class.java))
         /*   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
        }
    }

}
