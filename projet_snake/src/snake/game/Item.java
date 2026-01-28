package snake.game;

import java.util.Random;

public class Item {
    private Position pos;
    protected int value;

    public Item() {
        
    }

    public void newItem(Snake snake) {

        while (true) {
            Position position = new Position();

            int count = 0;
            for (Snake.Segment seg : snake.getSegments())
                if (!position.equals(seg.getPosition()))
                    count++;

            if (count==snake.getSegments().size())
                break;
        }

    }
}

public class Fruit extends Item {
    
    public Fruit() {
        value = 1;
    }
}
