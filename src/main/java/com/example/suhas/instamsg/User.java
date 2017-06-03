package com.example.suhas.instamsg;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.net.InetAddress;

/**
 * Created by suhas on 26/5/17.
 */

public class User {
    public static void sendMessage(String msg, InetAddress serverAddress){
        Log.d("User","Sending message: "+msg+" to: " +serverAddress.toString());
        ClientClass sender = new ClientClass(serverAddress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            sender.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, msg);
        else
            sender.execute(msg);
    }

    public static void receiveMessage(Context context){
        Log.d("User","Starting messageserver");
        MessageServer server = new MessageServer(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            server.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
        else
            server.execute((Void[])null);
    }
}
