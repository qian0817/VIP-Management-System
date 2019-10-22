package qianlei;

import java.io.File;

public class TestHelper {
    @SuppressWarnings("all")
    public static void deleteTestDb() {
        File file = new File("test.db");
        file.delete();
    }
}
