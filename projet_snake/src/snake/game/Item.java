package snake.game;

public class Item {
    protected Position pos;
    protected int value;
    protected String type;

    public Item(Map map) {
        pos = new Position(map);
    }

    public Position getPosition(){
        return pos;
    }

    public String getType() {
        return type;
    }

    // public void newItem(Snake snake) {

    //     while (true) {
    //         Position position = new Position();

    //         int count = 0;
    //         for (Snake.Segment seg : snake.getSegments())
    //             if (!position.equals(seg.getPosition()))
    //                 count++;

    //         if (count==snake.getSegments().size())
    //             break;
    //     }

    // }
}

class Fruit extends Item {
    
    public Fruit(Map map) {
        super(map);
        value = 1;
        type = "fruit";
    }
}
