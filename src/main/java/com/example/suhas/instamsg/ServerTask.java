package com.example.suhas.instamsg;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by suhas on 23/5/17.
 */

public class ServerTask extends AsyncTask<Void,Void,String> {
    public static final String EXTRA_MESSAGE = "com.example.suhas.instamsg.MESSAGE";
    Context context;
    ServerTask(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket client = serverSocket.accept();//Waits till client connects
            Log.d("ServerTask","Client has connected");
            DataInputStream DIS = new DataInputStream(client.getInputStream());
            String msg_received = DIS.readUTF();
            client.close();
            serverSocket.close();
            Log.d("ServerTask","Server has received the message: "+ msg_received);
            return msg_received;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ServerTask","Server failed",e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Intent i = new Intent(context,ChatActivity.class);
        i.putExtra(EXTRA_MESSAGE,result);
        context.startActivity(i);
    }
}

