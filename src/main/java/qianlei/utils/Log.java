package qianlei.utils;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 日志记录
 *
 * @author qianlei
 */
public final class Log {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd--hh-mm-ss-SSS");
    private static final DateFormat FILE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final Properties PROPS = System.getProperties();

    private Log() {
    }

    public static void error(Thread t, Throwable e) {
        new Thread(() -> {
            synchronized (Log.class) {
                String date = FILE_DATE_FORMAT.format(new Date());
                File file = new File("log/vip-manager-system-" + date + "-error.log");
                if (!file.getParentFile().exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    file.getParentFile().mkdirs();
                }
                try (FileOutputStream log = new FileOutputStream(file, true);
                     PrintStream printStream = new PrintStream(log, true, "UTF-8")
                ) {
                    printStream.println("java-version:" + PROPS.get("java.version"));
                    printStream.println("java-vendor:" + PROPS.get("java.vendor"));
                    printStream.println("os-name:" + PROPS.get("os.name"));
                    printStream.println("os-arch:" + PROPS.get("os.arch"));
                    printStream.println("os-version:" + PROPS.get("os.version"));
                    printStream.println(DATE_FORMAT.format(new Date()) + "  [" + t.getName() + "]");
                    e.printStackTrace();
                    e.printStackTrace(printStream);
                    printStream.println();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            String type;
            if (e instanceof SQLException) {
                type = "数据库错误";
            } else if (e instanceof IOException) {
                type = "IO错误";
            } else {
                type = "未知错误";
            }
            JOptionPane.showMessageDialog(null, "发生" + type + ",已记录到日志中");
        }).start();
    }

}
