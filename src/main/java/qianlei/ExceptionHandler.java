package qianlei;

import qianlei.utils.Log;

/**
 * 未捕获异常处理
 *
 * @author qianlei
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.error(t, e);
    }
}
