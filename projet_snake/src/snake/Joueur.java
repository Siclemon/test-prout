package snake;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Joueur {
    String pseudo;
    int money;
    int bestScore;
    List<Partie> games;
    Skins skins;
    Duration playTime;


    public Joueur(String pseudo){
        this.pseudo = pseudo;
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
        String[] colorsBackground;
    }

    public int getMoney() {
        return money;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }


}
