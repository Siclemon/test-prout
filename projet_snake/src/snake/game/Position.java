package snake.game;
import java.util.Random;

public class Position{
        private int y;
        private int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Position(Map map) {
            Random rng = new Random();
            while (true) {
                this.y = rng.nextInt(map.getHEIGHT());
                this.x = rng.nextInt(map.getWIDTH());
                if (map.getMap()[y][x].equals(""))
                    return;
            }
        }

        public int getY() {
            return y;
        }
        public int getX() {
            return x;
        }

        //@Override
        public boolean equals(Position pos2) {
            return (y==pos2.getY() && x==pos2.getX());
        }
    }

