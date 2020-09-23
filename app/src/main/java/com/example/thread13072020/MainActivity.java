package com.example.thread13072020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int a , b ,c;
    MyFlag myFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = b = c = 0;
        myFlag = new MyFlag();
        myFlag.position = 1;

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag){
                    for (int i = 1 ; i <= 1000 ; ) {
                        if (myFlag.position == 1){
                            a = i;
                            Log.d("BBB","A : " + a);
                            myFlag.position = 2;
                            myFlag.notifyAll();
                            i++;
                        }else{
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag){
                    for (int i = 1 ; i <= 1000 ;) {
                        if (myFlag.position == 2){
                            b = i;
                            Log.d("BBB","B : " + b);
                            myFlag.position = 3;
                            myFlag.notifyAll();
                            i++;
                        }else{
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag){
                    for (int i = 1 ; i <= 1000 ;) {
                        if (myFlag.position == 3){
                            c = a + b;
                            Log.d("BBB","C : " + c);
                            myFlag.position = 1;
                            myFlag.notifyAll();
                            i++;
                        }else{
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();


    }

}