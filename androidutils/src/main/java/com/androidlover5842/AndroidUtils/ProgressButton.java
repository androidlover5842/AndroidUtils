package com.androidlover5842.AndroidUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

public class ProgressButton extends LinearLayout {
    private ProgressBar progressBar;
    private Button button;
    private String loadingText;
    private String text;

    public ProgressButton(Context context) {
        super(context);
    }

    public ProgressButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.progress_button, this, true);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ProgressButton, 0, 0);
        progressBar= (ProgressBar) getChildAt(1);
        button= (Button) getChildAt(0);


        text=a.getString(R.styleable.ProgressButton_android_text);
        loadingText=a.getString(R.styleable.ProgressButton_loadingText);
        int background=a.getColor(R.styleable.ProgressButton_android_background,0);
        int textColor=a.getColor(R.styleable.ProgressButton_android_textColor,0);
        boolean start=a.getBoolean(R.styleable.ProgressButton_start,false);

        button.setTag(getId());
        progressBar.setTag(getId());
        if (background!=0)
            button.setBackgroundColor(background);

        if (textColor!=0)
            button.setTextColor(textColor);

        setStart(start);

        a.recycle();
    }

    public void setStart(boolean start) {
        progressBar.setVisibility(start?VISIBLE:GONE);
        setEnabled(!start);
        if (loadingText!=null && start)
            button.setText(loadingText);
        else button.setText(text);
    }

    public void setText(String text){
        this.text=text;
        if (text!=null)
            button.setText(text);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        button.setEnabled(enabled);
        progressBar.setEnabled(enabled);
    }

    public void setLoadingText(String loadingText) {
        this.loadingText = loadingText;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        button.setOnClickListener(l);
        progressBar.setOnClickListener(l);
        super.setOnClickListener(l);
    }
}
