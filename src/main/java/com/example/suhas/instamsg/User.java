package com.example.suhas.instamsg;

import android.content.Context;

import java.net.InetAddress;

/**
 * Created by suhas on 26/5/17.
 */

public class User {
    public static void sendMessage(String msg, InetAddress serverAddress){
        new ClientClass(serverAddress).execute(msg);

    }

    public static void receiveMessage(Context context){
        new MessageServer(context).execute();
    }
}
