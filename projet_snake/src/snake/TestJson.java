package snake;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestJson {
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Joueur player = new Joueur("fesse");
        player.setMoney(10);

        try (FileWriter file = new FileWriter("src\\snake\\baaaaatest.json");) {
            gson.toJson(player,file);
        }

    }
}
