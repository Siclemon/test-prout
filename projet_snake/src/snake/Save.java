package snake;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class Save {
    static final GsonBuilder builder = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting();
    static Gson gson = builder.create();
    static boolean newSave;
    private final static String FILE = "playerdata.json";

    public static void prout(Joueur player) throws IOException {
        List<Joueur> players;

        if (allSaveFiles() == null) players = new ArrayList<>();
        else players = new ArrayList<>(allSaveFiles());

        if (newSave) players.add(player);
        else {
            for (Joueur elem : players) {
                if (player.getPseudo().equals(elem.getPseudo())) {
                    players.remove(elem);
                    break;
                }
            }
            players.add(0,player);
        }
        

        try (FileWriter file = new FileWriter(FILE);) {
            gson.toJson(players, file);
        } catch (IOException e) {
        }


    }

    public static List<Joueur> allSaveFiles() {

        Type proutprout = new TypeToken<ArrayList<Joueur>>(){}.getType();

        FileReader reader = null;
        try {
            reader = new FileReader(FILE);
        } catch (FileNotFoundException ex) {
            System.getLogger(Save.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return gson.fromJson(reader,proutprout);
        
    }

    public static Joueur playerData(String pseudo) {

        List<Joueur> players = allSaveFiles();

        if (players!=null)
            for (Joueur elem : players) {
                if (elem.getPseudo().equals(pseudo)) return elem;
            }

        return newSave(pseudo);
    }

    public static Joueur newSave(String pseudo) {
        newSave = true;

        Joueur player = new Joueur(pseudo);
        Joueur.Skins skins = new Joueur.Skins();
        skins.colorBackgroundOne = "\033[48;2;47;138;40m";
        skins.colorBackgroundTwo = "\033[48;2;34;112;28m";
        skins.colorHeadFG = "\033[38;2;250;220;0m";
        skins.colorHeadBG = "\033[48;2;180;90;15m";
        skins.colorBodyFG = "\033[38;2;200;160;0m";
        skins.colorBodyBG = "\033[48;2;170;80;10m";
        skins.colorFoodFG = "\033[38;2;156;13;3m";
        skins.colorFoodBG = "\033[48;2;180;35;10m";
        
        return player;
    }
}
