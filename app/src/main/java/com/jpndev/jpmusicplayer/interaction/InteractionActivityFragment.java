package com.jpndev.jpmusicplayer.interaction;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jpndev.jpmusicplayer.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class InteractionActivityFragment extends Fragment {

	public InteractionActivityFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_interaction, container, false);
	}
}
