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

        final Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10 ; i++) {
                    Log.d("BBB","A : " + i);
                }
            }
        });
        final Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10 ; i++) {
                    Log.d("BBB","B : " + i);
                }
            }
        });

        threadA.start();
        threadB.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("BBB",threadA.getState().toString());
                Log.d("BBB",threadB.getState().toString());
            }
        },1000);

    }
}