package util;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Zhiyong Pan on 2021-01-16.
 */
public class StringArrays {
    public static String[] loadFromJsonFile(String filename) throws IOException, ParseException {
        return loadFromJson(new FileReader(filename));
    }

    private static String[] loadFromJson(Reader fileReader) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fileReader);
        // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        JSONArray ja = (JSONArray) obj;
        int n = ja.size();
        String[] a = new String[n];
        for (int i = 0; i < n; ++i) {
            a[i] = ja.get(i).toString();
        }
        return a;
    }
}
