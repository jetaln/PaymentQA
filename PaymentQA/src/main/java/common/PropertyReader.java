package common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static Properties properties;

    public PropertyReader() {
        read(System.getProperty("user.dir")+"/src/test/resources/automation.properties");
    }

    public static String getProperty(String key) {
        read("automation.properties");
        return properties.getProperty(key);
    }

    public static void read(String configfileName) {
        properties = new Properties();
        InputStream input = null;
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try(InputStream resourceStream = loader.getResourceAsStream(configfileName)) {
                properties.load(resourceStream);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("FILE NOT FUOND");
        }
        catch (IOException exception) {
            System.out.println("IO NOT FUOND");
        }
        catch (Exception exception) {
            System.out.println("Caught Exception");
        }
    }
}