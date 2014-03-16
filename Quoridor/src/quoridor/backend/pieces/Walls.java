package quoridor.backend.pieces;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import quoridor.backend.containers.Position;

/**
 * @author Team 4 Men And A Cripple
 *
 * This class represents the walls that exist in a game of Quoridor.
 */
public class Walls {

    /**
     * A map of all vertically blocked positions.
     */
    private Map<Position, Position> wallsv;

    /**
     * A map of all horizontally blocked positions.
     */
    private Map<Position, Position> wallsh;

    /**
     * The set of all root positions taken up by walls currently in the game.
     */
    private Set<Position> used;

    /**
     * Constructs a clean wall configuration.
     */
    public Walls() {
        wallsv = new TreeMap<Position, Position>();
        wallsh = new TreeMap<Position, Position>();
        used = new TreeSet<Position>();
    }

    /**
     * Determines if a wall can be placed given the current configuration.
     * 
     * @param a The root position of the wall to be placed.
     * @param b The position to determine the wall's orientation.
     * @return Whether the wall can be placed.
     */
    public boolean canAdd(Position a, Position b) {
        if (a.x > b.x || a.y > b.y) {
            Position temp = a;
            a = b;
            b = temp;
        }
        if (used.contains(a) || a.x < 0 || a.y < 0 || a.x > 7 || a.y > 7)
            return false;
        if (isVertical(a, b)) {
            Position c = new Position(a.x, a.y + 1);
            if (wallsv.containsKey(a) || wallsv.containsKey(c))
                return false;
        } else {
            Position c = new Position(a.x + 1, a.y);
            if (wallsh.containsKey(a) || wallsh.containsKey(c))
                return false;
        }
        return true;
    }

    /**
     * @return The map of all vertically blocked positions.
     */
    public Map<Position, Position> getWallsVertical() {
        return wallsv;
    }

    /**
     * @return The map of all horizontally blocked positions.
     */
    public Map<Position, Position> getWallsHorizontal() {
        return wallsh;
    }
    
    /**
     * Adds a new wall to the configuration.
     * 
     * @param a The root position of the wall to be placed.
     * @param b The position determining the wall's orientation.
     */
    public void add(Position a, Position b) {
        if (a.x > b.x || a.y > b.y) {
            Position temp = a;
            a = b;
            b = temp;
        }
        if (isVertical(a, b)) {
            used.add(a);
            wallsv.put(a, b);
            Position p = new Position(a.x, a.y + 1);
            Position q = new Position(b.x, b.y + 1);
            wallsv.put(p, q);
        } else {
            used.add(a);
            wallsh.put(a, b);
            Position p = new Position(a.x + 1, a.y);
            Position q = new Position(b.x + 1, b.y);
            wallsh.put(p, q);
        }
    }
    
    /**
     * Determines if a wall is blocking movement between two positions.
     * 
     * @param a The position to attempt to move from.
     * @param b The position to attempt to move to.
     * @return Whether the movement between these positions is blocked.
     */
    public boolean isBlocked(Position a, Position b) {
        if (a.x > b.x || a.y > b.y) {
            Position temp = a;
            a = b;
            b = temp;
        }
        if(isVertical(a, b))
            if(wallsv.containsKey(a))
                return wallsv.get(a).equals(b);
        if(wallsh.containsKey(a))
            return wallsh.get(a).equals(b);
        return false;
    }

    /**
     * @param p The position to check against.
     * @return The position, if any, blocked by a vertical wall.
     */
    public Position getMappedPositionVertical(Position p) {
        return wallsv.get(p);
    }

    /**
     * @param p The position to check against.
     * @return The position, if any, blocked by a horizontal wall.
     */
    public Position getMappedPositionHorizontal(Position p) {
        return wallsh.get(p);
    }

    /**
     * Determines if two positions are of vertical or horizontal orientation.
     * 
     * @param a The first position to consider.
     * @param b The second position to consider.
     * @return Whether the positions are of vertical orientation.
     */
    public boolean isVertical(Position a, Position b) {
        return a.y == b.y;
    }
    
    // TODO: Test by filling an imaginary game board with valid walls then try
    // to place invalid walls everywhere. Check output against expected output.

}
