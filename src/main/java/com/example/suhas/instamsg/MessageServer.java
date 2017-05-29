package com.example.suhas.instamsg;

import android.content.Context;
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
        return msg;
    }

    @Override
    protected void onPostExecute(String result){
        Log.d("MessageServer","MessageServer received message: "+result);
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        new MessageServer(context).execute();
    }
}
