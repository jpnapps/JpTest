package com.jpndev.jpmusicplayer.launchmode

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.jpndev.jpmusicplayer.R

import kotlinx.android.synthetic.main.activity_kotline_w.*

class KotlineActivityW : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotline_w)
        setSupportActionBar(toolbar)
        Snackbar.make(fab, "onCreate ", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        fab.setOnClickListener { view ->
          /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
            startActivity(Intent(this, KotlineActivityX::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Snackbar.make(fab, "onStart ", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
    }

    override fun onRestart() {
        super.onRestart()
        Snackbar.make(fab, "onRestart ", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
    }

    override fun onResume() {
        super.onResume()
       /* Snackbar.make(fab, "onResume ", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()*/
    }

}
