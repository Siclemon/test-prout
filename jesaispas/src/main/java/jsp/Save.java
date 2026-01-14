package jsp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    public void prout() throws IOException {
        // Gson gson = new Gson();

        Joueur siclemon = new Joueur("Siclemon");
        // siclemon.setMoney(99999);
        // siclemon.setBestScore(3);

        // try {
        //     gson.toJson(siclemon, new FileWriter("test.json"));
        // } catch (IOException e) {
        //     System.exit(1);
        // }

        Gson gson = new GsonBuilder().create();
        gson.toJson(siclemon);

        // JsonObject obj = new JSONObject();

        // obj.put("Pseudo", siclemon.pseudo);

        // final GsonBuilder builder = new GsonBuilder();
        // final Gson gson = builder.create();

        // FileWriter file = new FileWriter("test.json");
        // BufferedWriter bf = new BufferedWriter(file);
        // bf.write(obj.toJSONString);
        // bf.close();

        gson.toJson("siclemon", new FileWriter("test-prout\\jesaispas\\src\\main\\java\\test.json"));
    }
}
