package com.androidlover5842.example;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.androidlover5842.androidUtils.MeterView;
import com.androidlover5842.androidUtils.ProgressButton;
import com.androidlover5842.example.TestRecycler.TestAdapter;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private MeterView meterView;
    private TestAdapter adapter;
    private ProgressButton progressButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meterView=findViewById(R.id.meter);
        progressButton=findViewById(R.id.btProgress);
        progressButton.setOnClickListener(this);
        setupMeter();
    }


    private void setupMeter(){
        Timer timer=new Timer();
        AtomicInteger i= new AtomicInteger(0);
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(()->{
                    i.getAndIncrement();
                    if (i.get()==5) {
                        i.set(0);
                        progressButton.loading(false);
                    }

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

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==v.getId()){
            progressButton.loading(true);
        }
    }
}