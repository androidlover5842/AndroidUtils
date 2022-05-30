package com.androidlover5842.androidUtils.Validator;

import android.text.TextUtils;

public class StringValidator {

    private final String text;
    private final boolean empty;
    public static StringValidator getValidator(String text) {
        return new StringValidator(text);
    }

    private StringValidator(String text){
        this.text=text;
        empty=TextUtils.isEmpty(text);
    }

    public Checker addRule(Rule rule){
        boolean rulePassed=false;
        if (!empty)
            rulePassed=rule.rule(text);
        return new Checker(rulePassed,empty);
    }
}
