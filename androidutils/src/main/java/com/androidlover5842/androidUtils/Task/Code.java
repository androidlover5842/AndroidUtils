package com.androidlover5842.androidUtils.Task;


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
