package com.androidlover5842.AndroidUtils.Task;

import android.os.Handler;
import android.os.Looper;

import com.androidlover5842.AndroidUtils.BuildConfig;

import java.util.HashMap;
import java.util.Map;

public abstract class Runner implements Runnable{
    private RunnerStatus status=RunnerStatus.QUEUED;
    private HashMap<String,Object> pass;
    public abstract void exec() throws Exception;
    private resultListener result;
    private boolean debug;

    @Override
    public void run() {
        status=RunnerStatus.STARTED;
        try {
            exec();
        }catch (Exception e){
            if (debug)
                e.printStackTrace();
        }
        if (result!=null){
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                result.onResult(pass);
            },200);
        }
        status=RunnerStatus.ENDED;
    }

    public Runner setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public Runner setResultListener(resultListener listener){
        this.result=listener;
        return this;
    }


    public RunnerStatus getStatus(){
        return status;
    }

    public void passData(String key,Object val){
        if (pass==null)
            pass=new HashMap<>();
        pass.put(key,val);
    }

    public Map<String,Object> getResult() {
        return pass;
    }

    public interface resultListener{
        void onResult(HashMap<String,Object> map);
    }
}
