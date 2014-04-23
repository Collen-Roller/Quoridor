package quoridor.backend.containers;

/**
 * @author Team 4 Men And A Cripple
 *
 * This class represents a position in a game of Quoridor. This position is used
 * for player positions, wall positions, potential positions and anywhere a
 * position is required.
 */
public class Position implements Comparable<Position> {

    /**
     * The x coordinate of this position.
     */
    public int x;
    
    /**
     * The y coordinate of this position.
     */
    public int y;

    /**
     * Constructs a new position from the specified string encoded position.
     * 
     * @param pos The string encoding of this position.
     */
    public Position(String pos) {
        pos = pos.toLowerCase();
        x = (int) pos.charAt(0) - 97;
        y = (int) pos.charAt(1) - 49;
    }

    /**
     * Constructs a new position from the specified x and y coordinates.
     * 
     * @param x The x coordinate of this position.
     * @param y The y coordinate of this position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y+1;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "" + (char) (x + 97) + (char) (y + 49);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        Position p = (Position) o;
        if (p.x == x && p.y == y)
            return true;
        return false;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Position p) {
        if (x == p.x && y == p.y)
            return 0;
        if (x <= p.x || y <= p.y && (x != p.x && y != p.y))
            return 1;
        return -1;
    }

    // TODO: Write a test that creates a position at A1 and moves it to every
    // position on the board to I9 and checks for valid output.

}
