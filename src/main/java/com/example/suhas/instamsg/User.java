package com.example.suhas.instamsg;

import android.content.Context;
import android.util.Log;

import java.net.InetAddress;

/**
 * Created by suhas on 26/5/17.
 */

public class User {
    public static void sendMessage(String msg, InetAddress serverAddress){
        Log.d("User","Sending message: "+msg);
        new ClientClass(serverAddress).execute(msg);

    }

    public static void receiveMessage(Context context){
        Log.d("User","MessageServer started");
        new MessageServer(context).execute();
    }
}
