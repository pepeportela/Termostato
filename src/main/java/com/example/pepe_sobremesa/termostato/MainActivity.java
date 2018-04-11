package com.example.pepe_sobremesa.termostato;

import android.content.SyncStatusObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends AppCompatActivity {

    private Socket socket;
    private static final int SERVERPORT = 5000;
    private static final String SERVER_IP = "192.168.0.20";
    BufferedReader bufferedReader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new ClientThread()).start();
    }

    class ClientThread implements Runnable{

        @Override
        public void run() {
            try{
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr,SERVERPORT);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String pepe = bufferedReader.readLine();
                Log.d("APP",pepe);

            }catch (UnknownHostException e1){
                e1.printStackTrace();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }
}
