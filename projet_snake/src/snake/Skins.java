package snake;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Skins {
    final static GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
    static Gson gson = builder.create();

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

    public void newSkin(String type, String id, String[][] skin) {
        final String FILE = "src/snake/skins/" + type + ".json";
        Map<String,String[][]> list = getSkins(type);
        list.put(id, skin);


        try (FileWriter file = new FileWriter(FILE);) {
            gson.toJson(list, file);
        } catch (IOException e) {
        }
    }

    public Map<String,String> getSkinsAnimation(String item) {
        final String FILE = "src/snake/skins/animation/" + item + ".json";
        FileReader reader = null;
        Type type = new TypeToken<Map<String,String>>(){}.getType();

        try  {
            reader = new FileReader(FILE);
        } catch (FileNotFoundException e) {}

        return gson.fromJson(reader, type);
    }

    public void newSkinAnimation(String type, String id, String skin) {
        final String FILE = "src/snake/skins/animation/" + type + ".json";
        Map<String,String> list = getSkinsAnimation(type);
        list.put(id, skin);


        try (FileWriter file = new FileWriter(FILE);) {
            gson.toJson(list, file);
        } catch (IOException e) {
        }
    }

    public Map<String,String[]> getBackAnimation() {
        final String FILE = "src/snake/skins/animation/back.json";
        FileReader reader = null;
        Type type = new TypeToken<Map<String,String[]>>(){}.getType();

        try  {
            reader = new FileReader(FILE);
        } catch (FileNotFoundException e) {}

        return gson.fromJson(reader, type);
    }
}
