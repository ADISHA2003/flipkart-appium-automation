package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestDataReader {

    private static final String FILE = "testdata.properties";
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = TestDataReader.class.getClassLoader().getResourceAsStream(FILE)) {
            if (in == null) {
                throw new IllegalStateException("Could not find " + FILE + " on the classpath.");
            }
            PROPS.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + FILE, e);
        }
    }

    private TestDataReader() {}

    public static String get(String key) {
        String value = PROPS.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing test data key: " + key);
        }
        return value;
    }
}
