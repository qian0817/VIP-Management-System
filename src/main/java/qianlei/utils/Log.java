package qianlei.utils;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 日志记录
 *
 * @author qianlei
 */
public final class Log {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd--hh-mm-ss-SSS");
    private static final DateFormat FILE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final Properties PROPS = System.getProperties();
    private static final ThreadPoolExecutor LOG_THREAD_POOL = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100), r -> {
        Thread t = new Thread(r, "日志进程");
        t.setDaemon(true);
        return t;
    }, new ThreadPoolExecutor.DiscardPolicy());

    private Log() {
    }

    public static void error(Thread t, @NotNull Throwable e) {
        LOG_THREAD_POOL.execute(() -> {
            try {
                String date = FILE_DATE_FORMAT.format(new Date());
                File file = new File("log/vip-manager-system-" + date + "-error.log");
                boolean addTitle = false;
                if (!file.getParentFile().exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    file.getParentFile().mkdirs();
                    addTitle = true;
                }
                try (FileOutputStream log = new FileOutputStream(file, true);
                     PrintStream printStream = new PrintStream(log, true)
                ) {
                    if (addTitle) {
                        printStream.println("java-version:" + PROPS.get("java.version"));
                        printStream.println("java-vendor:" + PROPS.get("java.vendor"));
                        printStream.println("os-name:" + PROPS.get("os.name"));
                        printStream.println("os-arch:" + PROPS.get("os.arch"));
                        printStream.println("os-version:" + PROPS.get("os.version"));
                        printStream.println();
                    }
                    printStream.println(DATE_FORMAT.format(new Date()) + "  [" + t.getName() + "]");
                    e.printStackTrace();
                    e.printStackTrace(printStream);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        JOptionPane.showMessageDialog(null, "发生" + e.getClass().getName() + "异常,已记录到日志中");
    }
}
