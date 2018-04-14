package com.jpndev.jpmusicplayer.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by JAIGISH on 12-04-2018.
 */

@Dao
public interface StudentDao {

    @Insert
    public  void  addstudent(Student student);

    @Query("select * from student")
    public List<Student> getstudent();
}
