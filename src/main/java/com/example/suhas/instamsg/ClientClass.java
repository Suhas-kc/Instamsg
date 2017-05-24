package com.example.suhas.instamsg;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


/**
 * Created by suhas on 23/5/17.
 */

public class ClientClass extends AsyncTask {
    InetAddress serverAddress;
    ClientClass(InetAddress inetAddress){
        serverAddress = inetAddress;

    }

    @Override
    protected Object doInBackground(Object[] params) {
        Socket socket = null;
        try {
            socket = new Socket(serverAddress,8888);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DOS.writeUTF("ಕನ್ನಡ");
            socket.close();
            Log.d("ClientClass","Client finished sending message");

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ClientClass","Client failed",e);
        }

        return null;



    }
}
