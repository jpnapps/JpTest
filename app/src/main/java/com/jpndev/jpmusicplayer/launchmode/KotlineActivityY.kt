package com.jpndev.jpmusicplayer.launchmode

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.jpndev.jpmusicplayer.R

import kotlinx.android.synthetic.main.activity_kotline_y.*

class KotlineActivityY : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotline_y)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
     /*       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/

            startActivity(Intent(this, KotlineActivityW::class.java))
        }
    }

}
