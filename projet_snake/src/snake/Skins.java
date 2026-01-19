package snake;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Skins {
    static Gson gson = new Gson();

    public Map<String,String[][]> getSkins(String item) {
        final String FILE = "src/snake/skins/" + item + ".json";
        FileReader reader = null;
        Type type = new TypeToken<Map<String,String[][]>>(){}.getType();

        try  {
            reader = new FileReader(FILE);
        } catch (FileNotFoundException e) {}

        return gson.fromJson(reader, type);
    }

    public Map<String,String[]> getBack() {
        final String FILE = "src/snake/skins/back.json";
        FileReader reader = null;
        Type type = new TypeToken<Map<String,String[]>>(){}.getType();

        try  {
            reader = new FileReader(FILE);
        } catch (FileNotFoundException e) {}

        return gson.fromJson(reader, type);
    }
}
