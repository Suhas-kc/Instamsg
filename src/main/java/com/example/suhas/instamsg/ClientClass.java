package com.example.suhas.instamsg;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * Created by suhas on 23/5/17.
 */

public class ClientClass extends AsyncTask<String,Void,Void> {
    InetAddress serverAddress;
    ClientClass(InetAddress inetAddress){
        serverAddress = inetAddress;

    }

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected Void doInBackground(String... params) {
        Socket socket = null;
        if (params.length > 1 ) {
            Log.e("ClientClass","ClientClass got more than one string as input parameter");
            return null;
        }
        try {
            socket = new Socket(serverAddress,8888);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DOS.writeUTF(params[0]);
            socket.close();
            Log.d("ClientClass","Client finished sending message");

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ClientClass","Client failed",e);
        }
        return null;
    }


}
