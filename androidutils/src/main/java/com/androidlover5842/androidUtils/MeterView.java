package com.androidlover5842.androidUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MeterView  extends RelativeLayout {
    public MeterView(Context context) {
        super(context,null);
    }
    private ImageView indicator;
    private TextView textView;
    private ImageView meter;

    @SuppressLint("ResourceType")
    public MeterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ViewGroup v= (ViewGroup) LayoutInflater
                .from(context)
                .inflate(R.layout.meter_view,this);
        meter= (ImageView) v.getChildAt(0);
        indicator= (ImageView) v.getChildAt(1);
        textView= (TextView) v.getChildAt(2);
        meter.setId(025547);
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, meter.getId());
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textView.setLayoutParams(params);

    }

    public void setText(String text) {
        if (!TextUtils.isEmpty(text))
            textView.setText(text);
        else textView.setText("");
    }

    public void setPosition(int position){
        if (position==0) {
            indicator.setRotation(-105f);
            textView.setTextColor(getResources().getColor(R.color.dark_green));
        }
        else if (position==1) {
            indicator.setRotation(-60f);
            textView.setTextColor(getResources().getColor(R.color.light_green));
        }
        else if (position==2) {
            indicator.setRotation(0f);
            textView.setTextColor(getResources().getColor(R.color.orange));
        }
        else if (position==3) {
            indicator.setRotation(60f);
            textView.setTextColor(getResources().getColor(R.color.light_red));
        }
        else if (position==4) {
            indicator.setRotation(105f);
            textView.setTextColor(getResources().getColor(R.color.dark_red));
        }

    }
}
