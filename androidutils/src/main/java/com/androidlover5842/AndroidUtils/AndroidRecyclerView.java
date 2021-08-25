package com.androidlover5842.AndroidUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class AndroidRecyclerView extends RelativeLayout {
    private ViewGroup mContentView;
    private TextView textView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private String text;
    private RecyclerView.Adapter adapter;
    private boolean loading;
    private int height;
    private int width;
    public AndroidRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View v=LayoutInflater
                .from(context)
                .inflate(R.layout.empty_recycler,this);
        mContentView=v.findViewById(R.id.content);
        recyclerView= (RecyclerView) mContentView.getChildAt(0);
        progressBar= (ProgressBar) mContentView.getChildAt(1);
        textView= (TextView) mContentView.getChildAt(2);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.AndroidRecyclerView, 0, 0);

        text=a.getString(R.styleable.AndroidRecyclerView_android_text);
        loading=a.getBoolean(R.styleable.AndroidRecyclerView_loading,false);
        height=a.getLayoutDimension(R.styleable.AndroidRecyclerView_android_layout_height,ViewGroup.LayoutParams.WRAP_CONTENT);
        width=a.getLayoutDimension(R.styleable.AndroidRecyclerView_android_layout_width, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (text!=null)
            textView.setText(text);
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(width,height);

        mContentView.setLayoutParams(params);
        recyclerView.setLayoutParams(params);
        setLoading(loading);

        a.recycle();
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if(mContentView == null){
            super.addView(child, index, params);
        } else {
            mContentView.addView(child, index, params);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        this.adapter=adapter;
        if (adapter!=null){
            adapter.registerAdapterDataObserver(observer);
            recyclerView.setAdapter(adapter);
        }
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        recyclerView.setLayoutManager(layoutManager);
    }
    public void setText(String text){
        this.text=text;
        if (this.text!=null)
            textView.setText(text);
    }

    public void setLoading(boolean loading) {
        this.loading = loading;

        if (adapter!=null) {
            if (loading)
                textView.setVisibility(GONE);
            else {
                textView.setVisibility(adapter.getItemCount() != 0?GONE:VISIBLE);
            }
        }

        recyclerView.setVisibility(loading?GONE:VISIBLE);
        progressBar.setVisibility(loading?VISIBLE:GONE);
    }

    final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            setLoading(loading);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            setLoading(loading);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            setLoading(loading);
        }
    };

}
