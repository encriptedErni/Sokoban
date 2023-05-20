package es.upm.pproject.sokoban.model;

import java.util.HashMap;

import es.upm.pproject.sokoban.interfaces.Square;

public class Wall extends Square{
	 private Position position;

	 public Wall(Position position, HashMap<Position, Square> board) {
	        this.position = position;
	    }
	 
	 public boolean move(char way) {
		 return false;
	 }
	public Position getPosition() {
		return position;
	}
}
