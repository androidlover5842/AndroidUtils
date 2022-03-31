package com.androidlover5842.androidUtils.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.androidlover5842.androidUtils.BR;
import com.androidlover5842.androidUtils.Holder.BaseViewHolder;

import java.util.List;

public abstract class RecyclerBuilder<T> extends RecyclerView.Adapter<BaseViewHolder> implements OnItemClickListener<T> {

    private List<T> list;
    private int MAX_ITEM;
    private Context context;
    private int counter=0;
    private Activity activity;

    public RecyclerBuilder() {
    }

    public RecyclerBuilder(int MAX_ITEM)
    {
        this.MAX_ITEM=MAX_ITEM;
    }


    public List<T> getList() {
        return list;
    }

    public Context getContext()
    {
        return context;
    }
    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        counter++;
        View view=holder.getView();
        T model=getItem(position);
        view.setOnClickListener(v -> onClick(position,model,view));
        onBindViewHolder(holder,position,model,view);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null && parent.getContext()!=null)
        {
            context = parent.getContext();
        }

        ViewDataBinding view= DataBindingUtil.inflate(LayoutInflater.from(context),getLayoutId(),parent,false);;
        return new BaseViewHolder(view);
    }

    public T getItem(int position)
    {
        return list.get(position);
    }

    public abstract void onBindViewHolder(BaseViewHolder holder, int position, T model,View v);

    @Override
    public void onClick(int position, T model, View v) {

    }

    public boolean isLast(){
        if (getList().size()==counter)
        {
            return true;
        }
        else return false;
    }

    @Override
    public int getItemCount() {
        if (list!=null)
            if (MAX_ITEM==0 || MAX_ITEM >=list.size())
                return list.size();
            else return MAX_ITEM;
        else return 0;
    }
    public abstract @LayoutRes
    int getLayoutId();


    public void start(Class cls)
    {
        context.startActivity(new Intent(context,cls));
    }

    public void start(Class c, Bundle bundle)
    {
        context.startActivity(new Intent(context,c).putExtras(bundle));
    }
}