package com.androidlover5842.example.TestRecycler;

import android.view.View;

import com.androidlover5842.androidUtils.adapter.RecyclerBuilder;
import com.androidlover5842.androidUtils.Holder.BaseViewHolder;
import com.androidlover5842.example.R;

public class TestAdapter extends RecyclerBuilder<TestModel> {
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, TestModel model, View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
