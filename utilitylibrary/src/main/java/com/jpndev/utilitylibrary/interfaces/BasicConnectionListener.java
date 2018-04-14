package com.jpndev.utilitylibrary.interfaces;

/**
 * Created by User on 24-04-2016.
 */
public interface BasicConnectionListener {

    public void onIncomingConnectionDisconnected();
    public void onConnectionConnecting();
    public void onConnectionConnected();
    public void onConnectionFailedConnecting(Exception error);
    public void onConnectionDisconnecting();
    public void onConnectionDisconnected();
    public void onConnectionFailed(Exception error);

}
