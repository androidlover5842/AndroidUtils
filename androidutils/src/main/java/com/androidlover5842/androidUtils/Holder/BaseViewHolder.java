package com.androidlover5842.androidUtils.Holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private View view;
    private ViewDataBinding dataBinding;
    public BaseViewHolder(@NonNull ViewDataBinding itemView) {
        super(itemView.getRoot());
        this.view=itemView.getRoot();
        this.dataBinding=itemView;
    }

    public View getView() {
        return view;
    }

    public ViewDataBinding getDataBinding() {
        return dataBinding;
    }

    public <T> void dataBind(int variable,T model){
        DataBindingUtil.bind(view).setVariable(variable,model);
    }
}
