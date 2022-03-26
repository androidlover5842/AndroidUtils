package com.androidlover5842.androidUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public  class AndroidEditText extends LinearLayout {

    private TextInputEditText edittext;
    private TextInputLayout inputLayout;

    public AndroidEditText(Context context){
        super(context,null);

    }

    public AndroidEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_edit_text, this, true);

        inputLayout= (TextInputLayout) getChildAt(0);
        ViewGroup viewGroup= (ViewGroup) (inputLayout).getChildAt(0);
        edittext= (TextInputEditText) viewGroup.getChildAt(1);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.AndroidEditText, 0, 0);
        String text = a.getString(R.styleable.AndroidEditText_android_text);
        String hint = a.getString(R.styleable.AndroidEditText_android_hint);
        int maxLines = a.getInt(R.styleable.AndroidEditText_android_maxLines,1);
        int maxLength = a.getInt(R.styleable.AndroidEditText_android_maxLength,0);
        int inputType = a.getInt(R.styleable.AndroidEditText_android_inputType,0);
        boolean enabled = a.getBoolean(R.styleable.AndroidEditText_android_enabled,true);
        boolean focusable=a.getBoolean(R.styleable.AndroidEditText_android_focusable,true);
        edittext.setTag(getId());
        inputLayout.setTag(getId());
        setTag(getId());
        edittext.setFocusable(focusable);
        edittext.setEnabled(enabled);
        if (inputType!=0)
            setInputType(inputType);
        if (maxLength!=0)
            setMaxLength(maxLength);
        if (maxLines>1)
            setMaxLines(maxLines);
        {
            if (hint!=null )
                if (!TextUtils.isEmpty(hint))
            setHint(hint);
        }
        {
            if (text!=null)
                if (!TextUtils.isEmpty(text))
                    setText(text);
        }
        a.recycle();

    }


    public void setText(String text){
        if (text!=null)
        edittext.setText(text);
    }

    public void setHint(String hint){
        if (hint!=null)
            inputLayout.setHint(hint);
    }

    public String getHint(){
        return inputLayout.getHint().toString();
    }

    public void setInputType(int inputType){
        edittext.setInputType(inputType);
    }
    public void setMaxLines(int maxLines){
        edittext.setMaxLines(maxLines);
    }

    public int getMaxLines(){
        return edittext.getMaxLines();
    }

    public void setMaxLength(int maxLength){
        edittext.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }


    public String getText(){
        if (edittext.getText()==null)
            edittext.setText("");
        return edittext.getText().toString();
    }

    public TextInputEditText getEditText() {
        return edittext;
    }

    public TextInputLayout getInputLayout() {
        return inputLayout;
    }

    public void setChangeListener(TextChangeListener changeListener) {
        if (changeListener!=null)
        edittext.addTextChangedListener(changeListener);
    }

    public void setEnabled(boolean enabled){
        edittext.setEnabled(enabled);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        edittext.setOnClickListener(l);
        inputLayout.setOnClickListener(l);
        super.setOnClickListener(l);
    }
}