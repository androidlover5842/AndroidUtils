package com.androidlover5842.AndroidUtils;

public class FormValidator {

    private static FormValidator instance;

    public static FormValidator getInstance() {
        if (instance==null)
            instance=new FormValidator();
        return instance;
    }

    private FormValidator() {
    }
}
