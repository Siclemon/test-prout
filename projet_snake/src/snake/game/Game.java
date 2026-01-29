package snake.game;

import java.util.ArrayList;
import java.util.List;

import snake.Joueur;

public class Game {
    public void main(Joueur player) {
        Map map = new Map(10, 10);
        Snake snake = new Snake(5, 5);
        List<Item> items = new ArrayList<>();
        items.add(new Fruit(map));
        
    }

    void init() {
        
    }
}
