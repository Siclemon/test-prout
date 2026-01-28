package snake.game;
import java.util.Random;

public class Position{
        private int y;
        private int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Position() {
            Random rng = new Random();
            this.y = rng.nextInt(10);
            this.x = rng.nextInt(10);
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

