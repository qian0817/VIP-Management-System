package qianlei.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public final class LogUtil {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd--hh-mm-ss-SSS");
    private static final DateFormat FILE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final Properties PROPS = System.getProperties();

    private LogUtil() {
    }

    public static void error(Throwable e) {
        String date = FILE_DATE_FORMAT.format(new Date());
        File file = new File("log/vip-manager-system-" + date + "-error.log");
        if (!file.getParentFile().exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();
        }
        String threadName = Thread.currentThread().getName();
        new Thread(() -> {
            synchronized (LogUtil.class) {
                try (FileOutputStream log = new FileOutputStream(file, true);
                     PrintStream printStream = new PrintStream(log, true, "UTF-8")
                ) {
                    printStream.println("java-version:" + PROPS.get("java.version"));
                    printStream.println("java-vendor:" + PROPS.get("java.vendor"));
                    printStream.println("os-name:" + PROPS.get("os.name"));
                    printStream.println("os-arch:" + PROPS.get("os.arch"));
                    printStream.println("os-version:" + PROPS.get("os.version"));
                    printStream.println(DATE_FORMAT.format(new Date()) + "  [" + threadName + "]");
                    e.printStackTrace();
                    e.printStackTrace(printStream);
                    printStream.println();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

}
