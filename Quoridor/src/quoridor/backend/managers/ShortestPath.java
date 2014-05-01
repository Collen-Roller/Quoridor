package quoridor.backend.managers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import quoridor.backend.containers.Position;
import quoridor.backend.pieces.Pawn;
import quoridor.main.Quoridor;

public class ShortestPath {

	private ArrayList<Position> path;

	public ShortestPath(Pawn pa, Position po) {
		path = null;
		Set<Position> possibleMoves = pa.calcMoves();
		ArrayList<ArrayList<Position>> paths = new ArrayList<ArrayList<Position>>();
		for(Position p : possibleMoves)
			paths.add(genPath(p, po));
		Point p = new Point(0, 10000);
		for(int i = 0; i < paths.size(); i++)
			if(paths.get(i) != null && paths.get(i).size() < p.y)
				p = new Point(i, paths.get(i).size());
		if(p.y < 10000)
			path = paths.get(p.x);
	}

	private ArrayList<Position> genPath(Position p, Position po) {
		Node<Position> current = new Node<Position>(p, null);
		if(p.equals(po))
			return current.getPathToRoot();
		Queue<Node<Position>> nodes = new LinkedList<Node<Position>>();
		nodes.add(current);
		do {
			current = nodes.remove();
			current.addChild(new Position(current.getData().x + 1, current.getData().y));
	        current.addChild(new Position(current.getData().x, current.getData().y + 1));
	        current.addChild(new Position(current.getData().x - 1, current.getData().y));
	        current.addChild(new Position(current.getData().x, current.getData().y - 1));
	        Iterator<Node<Position>> itr = current.getChildren().iterator();
	        while(itr.hasNext()) {
	            Node<Position> pos = itr.next();
	            if(pos.getData().x < 0 || pos.getData().y < 0 || pos.getData().x > 8 || pos.getData().y > 8
	                    || Quoridor.getGameState().getWalls().isBlocked(pos.getData(), p))
	                itr.remove();
	        }
	        nodes.addAll(current.getChildren());
	        for(Node<Position> pos : current.getChildren())
	        	if(pos.getData().equals(po))
	        		return pos.getPathToRoot();
			} while(!nodes.isEmpty());
		return null;
	}
	
	public ArrayList<Position> getPath() {
		return path;
	}

}

class Node<T> {

	private T data;
	private Node<T> parent;
	private ArrayList<Node<T>> children;

	public Node(T data, Node<T> parent) {
		this.data = data;
		this.parent = parent;
		children = new ArrayList<Node<T>>();
	}

	public void addChild(T child) {
		if(!contains(new Node<T>(child, null))) {
			children.add(new Node<T>(child, this));
		}
	}

	public void removeChild(Node<T> child) {
		children.remove(child);
	}

	public Node<T> getParent() {
		return parent;
	}

	public ArrayList<Node<T>> getChildren() {
		return children;
	}

	public T getData() {
		return data;
	}

	public ArrayList<T> getPathToRoot() {
		Node<T> current = this;
		ArrayList<T> path = new ArrayList<T>();
		while(current != null) {
			path.add(current.getData());
			current = current.getParent();
		}
		Collections.reverse(path);
		return path;
	}

	public boolean equals(Object o) {
		if(!o.getClass().equals(this.getClass()))
			return false;
		return data.equals(((Node<T>) o).data);
	}

	public boolean contains(Node<T> node) {
		Node<T> current = this;
		int count = 0;
		while(current.getParent() != null) {
			count++;
			current = current.getParent();
		}
		Node<T> n = containsRecurse(current, node);
		if(n == null)
			return false;
		while(n.getParent() != null) {
			count--;
			n = n.getParent();
		}
		return count >= -1;
	}

	public Node<T> containsRecurse(Node<T> root, Node<T> node) {
		if(root.children.isEmpty() && !root.equals(node))
			return null;
		if(root.equals(node))
			return root;
		for(Node<T> n : root.getChildren()){
			Node<T> no = containsRecurse(n, node);
			if(no != null)
				return no;
		}	
		return null;
	}

	public String toString() {
		return data.toString() + (children.isEmpty() ? "" : children.toString());
	}

}
