package qianlei;

import java.io.File;

public class TestHelper {
    public static void deleteTestDb() {
        File file = new File("test.db");
        //noinspection ResultOfMethodCallIgnored
        file.delete();
    }
}
