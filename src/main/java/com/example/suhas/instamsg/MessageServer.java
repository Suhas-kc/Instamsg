package com.example.suhas.instamsg;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by suhas on 26/5/17.
 */

public class MessageServer extends ServerTask {
    MessageServer(Context context) {
        super(context);
    }

    @Override
    protected void onPostExecute(String result){

        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }
}
