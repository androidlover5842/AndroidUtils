package com.androidlover5842.androidUtils.Utils;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Utils {
    private static Context context;
    private Utils(){}
    public static void initialize(Context c){
        context=c;
    }

    public static LinearLayoutManager linear(@RecyclerView.Orientation int ORI){
        return new LayoutManager(context).linearLayoutManager(ORI,true);
    }

    public static LinearLayoutManager linear(@RecyclerView.Orientation int ORI,boolean scroll){
        return new LayoutManager(context).linearLayoutManager(ORI,scroll);
    }


    public static RecyclerView.LayoutManager gridLayoutManager(int MAX_ITEM){
        return new LayoutManager(context).grid(MAX_ITEM,true);
    }

    public static RecyclerView.LayoutManager gridLayoutManager(int MAX_ITEM,boolean scroll){
        return new LayoutManager(context).grid(MAX_ITEM,scroll);
    }



}
