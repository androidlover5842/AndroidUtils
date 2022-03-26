package com.androidlover5842.androidUtils.SharePref;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;

public class AndroidPref {
    private static AndroidPref instance;
    private Context context;
    String Pref="Pref";
    private Gson gson;

    public static AndroidPref getInstance() {
        if (instance==null)
            System.err.println("Please call AndroidPref.init(Context)");
        return instance;
    }

    public static void init(Context context){
        WeakReference<Context> weakReference=new WeakReference<>(context);
        instance=new AndroidPref(weakReference.get());
    }

    private AndroidPref(Context context){
        this.context=context;
        gson=new Gson();
    }

    public void setValue(String token, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Pref, MODE_PRIVATE).edit();
        editor.putString(token, value);
        editor.apply();

    }

    public void setValue(String token, Object value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Pref, MODE_PRIVATE).edit();
        editor.putString(token, Base64.encodeToString(gson.toJson(value).getBytes(Charset.defaultCharset()),0));
        editor.apply();
    }


    public Object getValue(String token) {
        SharedPreferences prefs = context.getSharedPreferences(Pref, MODE_PRIVATE);
        return prefs.getAll().get(token);
    }

    public  <T> T getValue(String id,Class<T> into){
        String val= (String) getValue(id);
        if (val!=null){
            T t=gson.fromJson(new String(Base64.decode(val,0),Charset.defaultCharset()),into);
            return t;
        }
        else return null;
    }
}
