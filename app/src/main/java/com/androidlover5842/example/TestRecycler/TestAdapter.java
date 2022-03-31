package com.androidlover5842.example.TestRecycler;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.androidlover5842.androidUtils.adapter.RecyclerBuilder;
import com.androidlover5842.androidUtils.Holder.BaseViewHolder;
import com.androidlover5842.example.BR;
import com.androidlover5842.example.models.TestModel;
import com.androidlover5842.example.R;

public class TestAdapter extends RecyclerBuilder<TestModel> {
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, TestModel model, View v) {
        dataBind(BR.model,model);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_test;
    }
}
