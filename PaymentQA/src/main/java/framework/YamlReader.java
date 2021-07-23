package framework;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class YamlReader {
    static InputStream inputStream;
    static Yaml yaml;

    public static HashMap<String, String> read(String value){
        try {
            inputStream = new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/appcredential.yml"));
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        yaml = new Yaml();
        HashMap<Object, Object> data = yaml.load(inputStream);
        return (HashMap<String, String>) data.get(value);
    }
}
