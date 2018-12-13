package com.jpndev.jpmusicplayer.roomdemo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jpndev.jpmusicplayer.roomdemo.util.Constants;

import java.util.List;

/**
 * Created by Mani-Ceino on 5/5/2018.
 */

@Dao
public interface ItemDao {

	@Query("SELECT * FROM "+ Constants.TABLE_NAME_ITEM)
	List<MItem> getNotes();

	/*
	* Insert the object in database
	* @param note, object to be inserted
	*/
	@Insert
	long insertNote(MItem note);

	/*
	* update the object in database
	* @param note, object to be updated
	*/
	@Update
	void updateNote(MItem repos);

	/*
	* delete the object from database
	* @param note, object to be deleted
	*/
	@Delete
	void deleteNote(MItem note);

	// Note... is varargs, here note is an array
    /*
    * delete list of objects from database
    * @param note, array of oject to be deleted
    */
	@Delete
	void deleteNotes(MItem... note);

}
