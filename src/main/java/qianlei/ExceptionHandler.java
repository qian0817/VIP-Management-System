package qianlei;

import qianlei.utils.Log;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.error(t, e);
    }
}
