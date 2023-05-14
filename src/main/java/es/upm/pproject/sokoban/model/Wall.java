package es.upm.pproject.sokoban.model;

import java.util.HashMap;

import es.upm.pproject.sokoban.interfaces.Square;

public class Wall extends Square{
	 private Position position;
	 private HashMap board = new HashMap<Position, Square>();

	 public Wall(Position position, HashMap<Position, Square> board) {
	        this.position = position;
	        this.board = board;
	    }
	 
	 public boolean move(char way) {
		 return false;
	 }
	public Position getPosition() {
		return position;
	}
}
