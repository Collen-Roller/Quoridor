package quoridor.backend.pieces;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import quoridor.backend.containers.Position;

public class Walls {

    private Map<Position, Position> wallsv;

    private Map<Position, Position> wallsh;

    private Set<Position> used;

    public Walls() {
        wallsv = new TreeMap<Position, Position>();
        wallsh = new TreeMap<Position, Position>();
        used = new TreeSet<Position>();
    }

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

    public Map<Position, Position> getWallsVertical() {
        return wallsv;
    }

    public Map<Position, Position> getWallsHorizontal() {
        return wallsh;
    }

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

    public Position getMappedPositionVertical(Position p) {
        return wallsv.get(p);
    }

    public Position getMappedPositionHorizontal(Position p) {
        return wallsh.get(p);
    }

    public boolean isVertical(Position a, Position b) {
        return a.y == b.y;
    }

}
