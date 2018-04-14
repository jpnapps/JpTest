package com.jpndev.jpmusicplayer.launchmode

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.jpndev.jpmusicplayer.R

import kotlinx.android.synthetic.main.activity_kotline_x.*

class KotlineActivityX : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotline_x)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
           /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
            startActivity(Intent(this, KotlineActivityY::class.java))
        }
    }

}
