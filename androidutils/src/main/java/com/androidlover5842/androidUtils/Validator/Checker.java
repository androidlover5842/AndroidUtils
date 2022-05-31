package com.androidlover5842.androidUtils.Validator;

public class Checker{
    private boolean rule;
    private boolean empty;

    public Checker(boolean rule,boolean empty) {
        this.rule=rule;
        this.empty=empty;
    }

    public Checker doOnEmptyOrRuleFailed(OnEmptyOrFailed onEmptyOrFailed){
        if (onEmptyOrFailed!=null)if (empty || !rule)
            onEmptyOrFailed.onEmptyOrFailed();
        return this;
    }

    public Checker doOnEmpty(OnEmpty onEmpty){
        if (empty&& onEmpty!=null)
            onEmpty.empty();
        return this;
    }

    public Checker doOnRuleFailed(OnRuleFailed failed){
        if (failed!=null && !rule && !empty)
            failed.onRuleFailed();
        return this;
    }

    public Checker doOnRulePassed(OnRulePassed rulePassed){
        if (toBool())
            rulePassed.onRulePassed();
        return this;
    }

    public Boolean toBool(){
        return !empty && rule;
    }
}
