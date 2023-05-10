package es.upm.pproject.sokoban.model;

import java.util.HashMap;

import es.upm.pproject.sokoban.interfaces.Square;

public class Wall {
	 private Position position;
	 private static HashMap board = new HashMap<Position, Square>();

	 public Wall(Position position, HashMap<Position, Square> board) {
	        this.position = position;
	        Wall.board = board;
	    }
	 
	 public boolean move(char way) {
		 return false;
	 }
}
