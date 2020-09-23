package com.example.thread13072020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int a, b, c;
    MyHandler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = b = c = 0;

        myHandler = new MyHandler();


        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putInt("A",a);
                message.setData(bundle);
                message.what = 1;
                myHandler.sendMessage(message);
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 2;
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putInt("B",a);
                message.setData(bundle);
                message.what = 2;
                myHandler.sendMessage(message);
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                c = a + b;
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putInt("C",c);
                message.setData(bundle);
                message.what = 3;
                myHandler.sendMessage(message);
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();


    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1 :
                    Toast.makeText(MainActivity.this, msg.getData().getInt("A") + "", Toast.LENGTH_SHORT).show();
                    break;
                case 2 :
                    Toast.makeText(MainActivity.this, msg.getData().getInt("B") + "", Toast.LENGTH_SHORT).show();
                    break;
                case 3 :
                    Toast.makeText(MainActivity.this, msg.getData().getInt("C") + "", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    }

}