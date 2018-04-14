package com.jpndev.jpmusicplayer.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by JAIGISH on 12-04-2018.
 */

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();
}
