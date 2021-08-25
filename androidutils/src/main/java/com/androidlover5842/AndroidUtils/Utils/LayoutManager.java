package com.androidlover5842.AndroidUtils.Utils;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LayoutManager {
    private Context context;
    public LayoutManager(Context context)
    {
        this.context=context;
    }

    public LinearLayoutManager linearLayoutManager(@RecyclerView.Orientation int ORI,boolean scroll)
    {

        LinearLayoutManager layoutManager=new LinearLayoutManager(context){
            @Override
            public boolean canScrollHorizontally() {
                return super.canScrollHorizontally() && scroll;
            }

            @Override
            public boolean canScrollVertically() {
                return super.canScrollVertically() && scroll;
            }
        };
        layoutManager.setOrientation(ORI);
        return layoutManager;
    }

    public RecyclerView.LayoutManager grid(int MAX_ITEM,boolean scroll)
    {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,MAX_ITEM){
            @Override
            public boolean canScrollHorizontally() {
                return super.canScrollHorizontally() && scroll;
            }

            @Override
            public boolean canScrollVertically() {
                return super.canScrollVertically() && scroll;
            }
        };
        return gridLayoutManager;
    }

}
