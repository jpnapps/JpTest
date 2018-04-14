package com.jpndev.jpmusicplayer.vogila

import android.app.ListActivity
import android.support.v7.app.AppCompatActivity
import android.content.ComponentName
import android.content.Context
import android.widget.Toast
import android.os.IBinder
import android.content.Intent
import android.text.method.TextKeyListener.clear
import android.widget.ArrayAdapter
import android.content.Context.BIND_AUTO_CREATE
import android.content.ServiceConnection
import android.os.Bundle
import android.view.View
import com.jpndev.jpmusicplayer.R
import java.util.*


/**
 * Created by Mani-Ceino on 3/25/2018.
 */
class VogilaActivity :ListActivity(),ServiceConnection {

    private var s: LocalWordService? = null

    //private val resultList = ArrayList<String>()

    //private var wordList: ArrayList<String>? = null
  //  private val wordList: ArrayList<String>? = ArrayList<String>()
    private val wordList = ArrayList<String>()
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vogeilla)
     //   wordList = ArrayList<String>()
        val random = Random()
        val input = Arrays.asList("Linux", "Android", "iPhone", "Windows7")
        wordList.add(input.get(random.nextInt(3)) + " " + 0)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, wordList!!)

        setListAdapter(adapter)
    }

    override fun onResume() {
        super.onResume()
     //   val intent = Intent(this, LocalWordService::class)
     val intent = Intent(this, LocalWordService::class.java)
        bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onPause() {
        super.onPause()
        unbindService(this)
    }

    private var adapter: ArrayAdapter<String>? = null
   // private var wordList: MutableList<String>? = null

    fun onClick(view: View) {
        when (view.getId()) {
            R.id.updateList -> if (s != null) {
                Toast.makeText(this, "Number of elements" + s!!.getWordList().size,
                        Toast.LENGTH_SHORT).show()
                wordList!!.clear()
                wordList!!.addAll(s!!.getWordList())
                adapter!!.notifyDataSetChanged()
            }
            R.id.triggerServiceUpdate -> {
              //  val service = Intent(this,LocalWordService::class)
                        val service = Intent(this, LocalWordService::class.java)
                this.startService(service)
            }
        }
    }

    override fun onServiceConnected(name: ComponentName, binder: IBinder) {
        val b = binder as LocalWordService.MyBinder
        s = b.getService()
        Toast.makeText(this@VogilaActivity, "Connected", Toast.LENGTH_SHORT).show()
    }

    override fun onServiceDisconnected(name: ComponentName) {
        s = null
    }
}