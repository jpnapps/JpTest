package com.jpndev.jpmusicplayer.vogila

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*
import java.util.Arrays.asList



/**
 * Created by Mani-Ceino on 3/25/2018.
 */
 class LocalWordService :Service(){

    /*private final IBinder mBinder = new MyBinder();
    private List<String> resultList = new ArrayList<String>();
    private int counter = 1;
*/
    private val mBinder = MyBinder()
    private val resultList = ArrayList<String>()
    private var counter = 1





    /*   @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        addResultValues();
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        addResultValues();
        return mBinder;
    }

    public class MyBinder extends Binder {
        LocalWordService getService() {
            return LocalWordService.this;
        }
    }

    public List<String> getWordList() {
        return resultList;
    }

    private void addResultValues() {
        Random random = new Random();
        List<String> input = Arrays.asList("Linux", "Android","iPhone","Windows7" );
        resultList.add(input.get(random.nextInt(3)) + " " + counter++);
        if (counter == Integer.MAX_VALUE) {
            counter = 0;
        }
    }*/


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        addResultValues()
        return Service.START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        addResultValues()
        return mBinder
    }

    inner class MyBinder : Binder() {
        /*internal val service: LocalWordService
            get() = this@LocalWordService*/
        fun getService():LocalWordService
        {
            return this@LocalWordService
        }

    }

    fun getWordList(): List<String> {
        return resultList
    }

    private fun addResultValues() {
        val random = Random()
        val input = Arrays.asList("Linux", "Android", "iPhone", "Windows7")
        resultList.add(input.get(random.nextInt(3)) + " " + counter++)
        if (counter === Integer.MAX_VALUE) {
            counter = 0
        }
    }
}