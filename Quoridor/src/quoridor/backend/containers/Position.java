package quoridor.backend.containers;

public class Position implements Comparable<Position> {

    public int x, y;

    public Position(String pos) {
        pos = pos.toLowerCase();
        x = (int) pos.charAt(0) - 97;
        y = (int) pos.charAt(1) - 49;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "" + (char) (x + 97) + (char) (y + 49);
    }

    @Override
    public boolean equals(Object o) {
        Position p = (Position) o;
        if (p.x == x && p.y == y)
            return true;
        return false;
    }

    @Override
    public int compareTo(Position p) {
        if (x == p.x && y == p.y)
            return 0;
        if (x <= p.x || y <= p.y && (x != p.x && y != p.y))
            return 1;
        return -1;
    }

}
