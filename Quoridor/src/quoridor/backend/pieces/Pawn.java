package quoridor.backend.pieces;

import java.awt.Image;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import quoridor.backend.containers.Position;
import quoridor.main.Quoridor;
import quoridor.network.client.NetworkClient;

public class Pawn {

    private Position pos;
    private NetworkClient networkClient;
    private final String moveRegex = "[a-i][1-9][h,v]|[a-h][1-8]";
    private Image pawn;
<<<<<<< HEAD
=======
    
    
    //sprivate Map<Position, Position> locations;
>>>>>>> f28b4896560d79f2353326088bfcefa4ebbedac1

    // TODO: Name the pawns
    //Why Do Pawns have to be named? each player object exists
    //each pawn object exists with a specific pawn
    //checkout init state how thats set up. 

    public Pawn(String pos, Image p) {
<<<<<<< HEAD
=======
        //this.locations = new TreeMap<Position, Position>;
>>>>>>> f28b4896560d79f2353326088bfcefa4ebbedac1
        this.pos = new Position(pos);
        this.pawn = p;
    }
    
    public Image getPawn(){
    	return pawn;
    }
    
    public Set<Position> calcMoves() {
        Set<Position> moves = new TreeSet<Position>();
        moves.add(new Position(pos.x + 1, pos.y));
        moves.add(new Position(pos.x, pos.y + 1));
        moves.add(new Position(pos.x - 1, pos.y));
        moves.add(new Position(pos.x, pos.y - 1));
        Iterator<Position> itr = moves.iterator();
        while(itr.hasNext()) {
            Position p = itr.next();
            if(p.x < 0 || p.y < 0 || p.x > 8 || p.y > 8)
                itr.remove();
        }
        return moves;
    }

    public boolean startNetwork(String host) {
        try {
            networkClient = new NetworkClient(host);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public NetworkClient getClient(){
    	return networkClient;
    }

    public String getMove() {
        networkClient.sendString("MOVE?");
        String s = networkClient.getString();
        Pattern r = Pattern.compile(moveRegex);
        Matcher m = r.matcher(s);
        if(m.find())
            return s;
        return "ERROR";
    }

    public void move(String s) {
        pos = new Position(s);
    }

    public void boot() {
        for(Pawn p : Quoridor.getGameState().getPawns())
            p.networkClient.sendString("BOOTED PLAYER");
        networkClient.close();
    }

    public Position getPosition() {
        return pos;
    }

    public String toString() {
        return "Pawn at: " + pos;
    }

    public void setPosition(Position pos) {
        this.pos = pos;
    }

}

