package com.androidlover5842.example;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidlover5842.example.TestRecycler.TestAdapter;
import com.androidlover5842.example.models.MeterModel;
import com.androidlover5842.example.models.TestModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<MeterModel> meterLiveData;
    private MeterModel meterModel;
    private String[] meterText={
            "Attractive",
            "Very Attractive",
            "Fair Price",
            "Expensive",
            "Very Expensive"
    };

    private Handler handler;
    private int counter;
    private static final int handlerDelay = 1000;
    private List<TestModel> testModels;
    private TestAdapter adapter;

    public MainViewModel(){
        meterLiveData=new MutableLiveData<>();
        handler=new Handler();
        meterModel=new MeterModel();
        testModels=new ArrayList<>();
        adapter=new TestAdapter();
        init();
    }
    private void init(){
        setupTestList();
        setupMeter();
        new Handler().postDelayed(()->{
            adapter.setList(testModels);
        },3000);
    }

    private void setupMeter(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                counter++;
                if (counter==5){
                    counter=0;
                }
                meterModel.setPosition(counter);
                meterModel.setText(meterText[counter]);
                meterLiveData.postValue(meterModel);
                handler.postDelayed(this,handlerDelay);
            }
        }, handlerDelay);

    }

    public LiveData<MeterModel> getCounter() {
        return meterLiveData;
    }


    public TestAdapter getAdapter() {
        return adapter;
    }

    private void setupTestList(){
        for (int i = 0; i < 100; i++) {
            testModels.add(new TestModel("Android","World"));
        }
    }
}
