package com.jpndev.jpmusicplayer.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.jpndev.jpmusicplayer.roomdemo.util.Constants;

/**
 * Created by Mani-Ceino on 5/5/2018.
 */

@Database(entities = {MItem.class}, version = 1)
public abstract  class ItemDatabase extends RoomDatabase {


	public abstract ItemDao getItemDao();


	private static ItemDatabase itemDB;

	// synchronized is use to avoid concurrent access in multithred environment
	public static /*synchronized*/ ItemDatabase getInstance(Context context) {
		if (null == itemDB) {
			itemDB = buildDatabaseInstance(context);
		}
		return itemDB;
	}

	private static ItemDatabase buildDatabaseInstance(Context context) {
		return Room.databaseBuilder(context,
				ItemDatabase.class,
				Constants.DB_NAME).allowMainThreadQueries().build();
	}

	public  void cleanUp(){
		itemDB = null;
	}
}
