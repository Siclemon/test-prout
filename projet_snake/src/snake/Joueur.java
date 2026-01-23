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
        final int score;
        final LocalDateTime date;
        final String pseudo;

        public Partie(int score, LocalDateTime date, String pseudo) {
            this.score = score;
            this.date = date;
            this.pseudo = pseudo;
        }

        public int getScore() {
            return score;
        }

        public LocalDateTime getDate(){
            return date;
        }
    }

    public static class Skins {
        String fruit;
        String head;
        String body;
        String back;

        public Skins() {
            this.fruit = "red";
            this.head = "yellow";
            this.body = "yellow";
            this.back = "green";
        }

        public String getFruit(){
            return fruit;
        }

        public void setFruit(String fruit){
            this.fruit = fruit;
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

    public List<Joueur.Partie> getGames() {
        return games;
    }
}
