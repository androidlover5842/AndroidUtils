package com.androidlover5842.AndroidUtils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;

public class BookMarkView extends RelativeLayout {
    private AppCompatImageView imageView;
    private boolean enabled;
    private int enabledTint;
    private int disabledTint;
    public BookMarkView(Context context) {
        super(context,null);
    }

    public BookMarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewGroup v= (ViewGroup) LayoutInflater
                .from(context)
                .inflate(R.layout.bookmark_view,this);
        imageView= (AppCompatImageView) v.getChildAt(0);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.BookMarkView, 0, 0);
        enabled=a.getBoolean(R.styleable.BookMarkView_enabled,false);
        enabledTint=a.getColor(R.styleable.BookMarkView_enabledTint,getResources().getColor(R.color.black));
        disabledTint=a.getColor(R.styleable.BookMarkView_enabledTint,getResources().getColor(R.color.black));

        setChecked(enabled);

        a.recycle();
    }

    public void setChecked(boolean enabled){
        this.enabled=enabled;
        imageView.setImageResource(enabled?R.drawable.ic_bookmark:R.drawable.ic_bookmark_disabled);
        imageView.setColorFilter(enabled?enabledTint:disabledTint, android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public void setDisabledTint(int disabledTint) {
        this.disabledTint = disabledTint;
    }

    public void setEnabledTint(int enabledTint) {
        this.enabledTint = enabledTint;
    }

    public boolean isChecked() {
        return enabled;
    }
}
