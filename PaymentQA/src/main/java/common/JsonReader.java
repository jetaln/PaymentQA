package common;


import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    public static String getResource(String fileName, String resourceKey) {
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(System.getProperty("user.dir") + "/src/test/resources/"+fileName));

            JSONObject obj = new JSONObject(object.toString());
            String key = obj.getJSONObject("json").getString(resourceKey);
            return key;
        } catch (FileNotFoundException exception) {
            System.out.println("FILE NOT FOUND");
        } catch (ParseException exception) {
            System.out.println("PARSE Exception");
        } catch (IOException exception) {
            System.out.println("IO Exception");
        }
        return "EXCEPTION";
    }
}

