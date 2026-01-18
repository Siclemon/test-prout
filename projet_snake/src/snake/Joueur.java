package snake;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private final String pseudo;
    private int money;
    private int highScore;
    List<Partie> games;
    Skins skins;
    //Duration playTime;


    public Joueur(String pseudo){
        this.pseudo = pseudo;
        this.money = 0;
        this.highScore = 0;
        this.skins = new Skins();
        this.games = new ArrayList<>();
    }

    public static class Partie {
        int score;
        LocalDateTime date;

        public Partie(int score, LocalDateTime date) {
            this.score = score;
            this.date = date;
        }
    }

    public static class Skins {
        String colorHeadFG;
        String colorHeadBG;
        String colorBodyFG;
        String colorBodyBG;
        String colorFoodFG;
        String colorFoodBG;
        String colorBackgroundOne;
        String colorBackgroundTwo;

        public Skins() {
            this.colorBackgroundOne = "\033[48;2;47;138;40m";
            this.colorBackgroundTwo = "\033[48;2;34;112;28m";
            this.colorHeadFG = "\033[38;2;250;220;0m";
            this.colorHeadBG = "\033[48;2;180;90;15m";
            this.colorBodyFG = "\033[38;2;200;160;0m";
            this.colorBodyBG = "\033[48;2;170;80;10m";
            this.colorFoodFG = "\033[38;2;156;13;3m";
            this.colorFoodBG = "\033[48;2;180;35;10m";
        }
    }

    public int getMoney() {
        return money;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getPseudo() {
        return pseudo;
    }


}
