package com.example.suhas.instamsg;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by suhas on 26/5/17.
 */

public class MessageServer extends ServerTask {
    MessageServer(Context context) {
        super(context);
    }

    @Override
    protected String doInBackground(Void... params){

        String msg = super.doInBackground();
        Log.d("MessageServer","MessageServer started");
        Log.d("MessageServer","MessageServer received: " + msg);
        return msg;
    }

    @Override
    protected void onPostExecute(String result){
        Log.d("MessageServer","MessageServer received message: "+result);
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        MessageServer server = new MessageServer(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            server.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
        else
            server.execute((Void[])null);
    }
}
