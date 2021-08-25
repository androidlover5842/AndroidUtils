package com.androidlover5842.AndroidUtils;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class TextChangeListener implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        newText(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    public abstract void newText(String text);
}
