package com.teenenggr.stepper_rpi_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.socketio.client.IO;

public class MainActivity extends AppCompatActivity {


    Button upButton, downButton, connectButton;
    Boolean isSocketConnected = false;

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.0.105:8080");
        } catch (Exception e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upButton = (Button) findViewById(R.id.up_button_ID);
        downButton= (Button) findViewById(R.id.down_button_ID);
        connectButton= (Button) findViewById(R.id.connect_button_ID);

        // Add Listeners

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSocketConnected) {
                    disconnectFromSocket();
                    connectButton.setText("Connect");
                } else {
                    connectToSocket();
                    connectButton.setText("Disconnect");
                }
                isSocketConnected = !isSocketConnected;
            }
        });

        upButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case 0:
                        upButtonStartTouch();
                        break;
                    case 1:
                        upButtonEndTouch();
                        break;
                }
                return false;
            }
        });

        downButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case 0:
                        downButtonStartTouch();
                        break;
                    case 1:
                        downButtonEndTouch();
                        break;
                }
                return false;
            }
        });

    }

    void connectToSocket() {
        mSocket.connect();
    }


    void disconnectFromSocket() {
        mSocket.disconnect();
    }

    void upButtonStartTouch() {
        mSocket.emit("motor",1);
    }

    void upButtonEndTouch() {
        mSocket.emit("motor",0);
    }

    void downButtonStartTouch() {
        mSocket.emit("motor",2);
    }

    void downButtonEndTouch() {
        mSocket.emit("motor",0);
    }
}
