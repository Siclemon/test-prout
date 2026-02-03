package snake.game;

import java.util.HashMap;
import java.util.List;

import snake.game.Snake.Direction;

public class Map {
    final private int HEIGHT;
    final private int WIDTH;
    private String[][] map;
    private String[][] displayMap;
    private Direction[][] direcs;
    private Skins skins;

    public Map(int height, int width) {
        HEIGHT = height;
        WIDTH = width;
        map = new String[height][width];
        displayMap = new String[height*4][width*8];
    }

    public String[][] getMap() {
        return map;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void init() {
        for (int y=0; y<HEIGHT; y++) {
            for (int x=0; x<WIDTH; x++) {
                map[y][x] = "";
            }
        }
    }

    public void update(Snake snake, List<? extends Item> items) {
        init();
        
        for (int i=0; i<snake.getSegments().size(); i++) {
            caca(snake.getSegments().get(i),i);
        }

        for (Item item : items) {
            caca(item);
        }

    }

    public void caca(Snake.Segment seg, int id) {
        int y;
        int x;

        y = seg.getPosition().getY();
        x = seg.getPosition().getX();

        map[y][x] = id+"";
        direcs[y][x] = seg.getDirection();
    }

    public void caca(Item it) {
        int y;
        int x;

        y = it.getPosition().getY();
        x = it.getPosition().getX();

        map[y][x] = it.getType();
    }

    public void uupdateDisplay(HashMap<K,V> skins) {

        for (int y=0; y<displayMap.length; y++) {
            for (int x=0; x<displayMap[y].length; x++) {
                String bit = "▀";
                if (map[y/4][x/8].length()==1)
            }
        }
    }

    public void updateDisplay(HashMap skins) {
        
    }
    
}
