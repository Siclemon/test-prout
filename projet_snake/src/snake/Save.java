package snake;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

//import snake.Joueur;

public class Save {
    public void prout() throws IOException {
        Joueur player = new Joueur("siclemon");
        Gson gson = new Gson();
        

        player.setBestScore(300000);
        player.setMoney(1000000);

        try (FileWriter file = new FileWriter("./snake/test.json");) {
            gson.toJson(player, file);
        } catch (IOException e) {
        }


    }
}
