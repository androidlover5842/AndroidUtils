package com.androidlover5842.androidUtils.adapter;

import android.view.View;

public interface OnItemClickListener<T> {
    void onClick(int position, T model, View v);
}
