package com.example.thread13072020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                xuly("A");
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                xuly("B");
            }
        });

        threadA.start();
        threadB.start();
    }
    private synchronized void xuly(String tag){
        for (int i = 0; i <= 1000 ; i++) {
            Log.d("BBB",tag + " : " + i);
        }
    }
}