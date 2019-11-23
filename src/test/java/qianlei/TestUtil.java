package qianlei;

import java.io.File;

public class TestUtil {
    public static void deleteTestDb() {
        File file = new File("test.db");
        //noinspection ResultOfMethodCallIgnored
        file.delete();
    }
}
