package com.jpndev.jpmusicplayer.roomdemo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Mani-Ceino on 5/5/2018.
 */

@Entity
public class MItem implements Serializable {

	@PrimaryKey(autoGenerate = true)
	private int _id;


	private String name;

	@ColumnInfo(name="content")
	private String description;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MItem(int _id, String name, String description) {
		this._id = _id;
		this.name = name;
		this.description = description;
	}

	public MItem(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
