package jsp;

public class Joueur {
    String pseudo;
    int money;
    int bestScore;


    public Joueur(String pseudo){
        this.pseudo = pseudo;
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
