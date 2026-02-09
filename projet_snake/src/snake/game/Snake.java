package snake.game;

import java.util.ArrayList;
import java.util.List;

//import snake.game.Position;

public class Snake {
    private List<Segment> segments;

    public Snake(int yHead, int xHead) {
        segments = new ArrayList<>();
        segments.add(new Segment(new Position(yHead, xHead), Direction.UP));
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public class Segment{
        Position pos;
        Direction dir;

        public Segment(Position pos, Direction dir) {
            this.pos = pos;
            this.dir = dir;
        }

        public Position getPosition() {
            return pos;
        }
        public Direction getDirection() {
            return dir;
        }

    }

    public enum Direction {
        UP(-1,0),
        DOWN(1,0),
        LEFT(0,-1),
        RIGHT(0,1);

        private final int dy, dx;

        private Direction(int dy, int dx) {
            this.dy = dy;
            this.dx = dx;
        }

        public int getDy() {
            return dy;
        }
        public int GetDx() {
            return dx;
        }
    }

    public void move(Direction dir) {
        Segment head = segments.get(0);
        Position newPos = new Position(head.pos.getY()+dir.getDy(), head.pos.getX()+dir.GetDx());
        Segment newHead = new Segment(newPos, dir);
        segments.add(0,newHead);
    }
}
