package com.androidlover5842.AndroidUtils.Task;


public abstract class Code {
    public abstract void run();
    private boolean cancel;

    public void cancel() {
        this.cancel = true;
    }

    public boolean isCancel() {
        return cancel;
    }
}
