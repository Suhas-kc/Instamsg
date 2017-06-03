package com.example.suhas.instamsg;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.example.suhas.instamsg.ServerTask.EXTRA_MESSAGE;
import static com.example.suhas.instamsg.User.receiveMessage;
import static com.example.suhas.instamsg.User.sendMessage;

public class ChatActivity extends AppCompatActivity {
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText msg = (EditText) findViewById(R.id.Msg);
        Button sendMsg = (Button) findViewById(R.id.sendMsg);
        Button quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
            }
        });
        Intent i = getIntent();
        String serverAddress = i.getStringExtra(EXTRA_MESSAGE);

        try {
            final InetAddress inetAddress = InetAddress.getByName(serverAddress);
            Log.d("ChatActivity","Server address is : "+serverAddress);
            sendMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt = msg.getText().toString();
                    if (txt.length() != 0){
                        sendMessage(txt,inetAddress);
                    }
                }
            });

        } catch (UnknownHostException e) {
            Log.e("ChatActivity","Unknown host exception : " + e);

        }
        receiveMessage(this.getApplicationContext());



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

}
