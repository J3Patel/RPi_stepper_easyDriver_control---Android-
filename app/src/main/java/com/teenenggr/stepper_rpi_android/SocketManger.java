package com.teenenggr.stepper_rpi_android;

/**
 * Created by Jatin on 5/24/2018.
 */

import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.socketio.client.IO;

public class SocketManger {
    static SocketManger sharedInstance = new SocketManger();
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("");
        }catch (Exception e) {}
    }

    SocketManger() {

    }
}
