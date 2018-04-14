package com.jpndev.utilitylibrary.interfaces;

/**
 * Created by User on 24-04-2016.
 */
public interface BasicDeviceListener {

    public void onDeviceStartedListening();
    public void onDeviceStoppedListening(Exception error);

}
