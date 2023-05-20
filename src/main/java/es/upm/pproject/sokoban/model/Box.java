package es.upm.pproject.sokoban.model;

import java.util.Map;

import es.upm.pproject.sokoban.interfaces.Square;

public class Box implements Square {

	private Position position;
	private Map<Position, Square> board;

	public Box(Position position, Map<Position, Square> board) {
		this.position = position;
		this.board = board;
	}

	public boolean move(char way) {
			 return false;
		 }
	public Position getPosition() {
			return position;
		}
	public Map<Position, Square> getBoard() {
		return board;
	}
}
