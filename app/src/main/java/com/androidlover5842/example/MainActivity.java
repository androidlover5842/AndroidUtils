package com.androidlover5842.example;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.androidlover5842.AndroidUtils.MeterView;
import com.androidlover5842.example.TestRecycler.TestAdapter;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends FragmentActivity {

    private MeterView meterView;
    private TestAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meterView=findViewById(R.id.meter);

        setupMeter();
    }


    private void setupMeter(){
        Timer timer=new Timer();
        AtomicInteger i= new AtomicInteger(1);
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(()->{
                    System.out.println("Ypdating ...."+i.get());
                    i.getAndIncrement();
                    if (i.get()==5)
                        i.set(0);

                    meterView.setPosition(i.get());
                    meterView.setText(text(i.get()));

                });
            }
        };
        timer.scheduleAtFixedRate(timerTask,0,1000);
    }

    private String text(int position){
        String text="";
        switch (position){
            case 0:
                text="Attractive";
                break;
            case 1:
                text="Very Attractive";
                break;
            case 2:
                text="Fair Price";
                break;
            case 3:
                text="Expensive";
                break;
            case 4:
                text="Very Expensive";
                break;
        }
        return text;
    }
}