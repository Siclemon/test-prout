package snake;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestJson {
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
        HashMap<String,String[]> caca = new HashMap<>();

        caca.put("green", new String[] {"\u001b[48;2;100;250;100m",
    "\u001b[38;2;47;138;40m\u001b[48;2;47;138;40m",
    "\u001b[38;2;34;112;28m\u001b[48;2;34;112;28m"});

        //caca.put("10", new int[][] {{9,4},{7,9,5,3}});

        try (FileWriter file = new FileWriter("src/snake/skins/back.json");) {
            gson.toJson(caca,file);
        }
        HashMap<String,String[]> pipi = caca;

        //System.out.println(pipi.get("red")[1][4]+"▀");

    }
}
