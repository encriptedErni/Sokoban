package es.upm.pproject.sokoban.model;

import java.util.Map;

import es.upm.pproject.sokoban.interfaces.Square;

public class GoalPosition implements Square {

	 private Position position;
	 private Map<Position, Square> board;

	 public GoalPosition(Position position, Map<Position, Square> board) {
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
