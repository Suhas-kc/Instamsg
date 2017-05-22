package com.example.suhas.instamsg;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v4.util.LogWriter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    WifiP2pManager managerObj;
    WifiP2pManager.Channel channelObj;
    WiFiDirectBroadcastReceiver receiverObj;
    IntentFilter filterObj;
    TextView macAddress;
    WifiP2pConfig deviceConfig;
    final List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();


    private WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peerList) {

            Collection<WifiP2pDevice> refreshedPeers = peerList.getDeviceList();
            if (!refreshedPeers.equals(peers)) {
                peers.clear();
                peers.addAll(refreshedPeers);
                String temp = "";
                // If an AdapterView is backed by this data, notify it
                // of the change.  For instance, if you have a ListView of
                // available peers, trigger and
                for(WifiP2pDevice phone:peers){
                    temp = temp + phone.deviceName + "\n";
                }

                macAddress.setText(temp);
                // Perform any other updates needed based on the new list of
                // peers connected to the Wi-Fi P2P network.
            }

            if (peers.size() == 0) {
                macAddress.setText("No peers found :(");
            }
            else{
                deviceConfig = new WifiP2pConfig();
                deviceConfig.deviceAddress = peers.get(0).deviceAddress;
                managerObj.connect(channelObj, deviceConfig, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        macAddress.setText("Connection to " + deviceConfig.deviceAddress + "is successful");
                    }

                    @Override
                    public void onFailure(int reason) {

                    }
                });

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        macAddress = (TextView)findViewById(R.id.testDev);

        managerObj = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channelObj = managerObj.initialize(this,getMainLooper(),null);
        receiverObj = new WiFiDirectBroadcastReceiver(managerObj,channelObj,this,peerListListener);

        filterObj = new IntentFilter();
        filterObj.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        filterObj.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        filterObj.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        filterObj.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);


        managerObj.discoverPeers(channelObj, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                //Log or leave empty
            }

            @Override
            public void onFailure(int reason) {
                //Log or leave empty
            }
        });






    }
    /* register the broadcast receiver with the intent values to be matched */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiverObj, filterObj);
    }
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiverObj);
    }



}


