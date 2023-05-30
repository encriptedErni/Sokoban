package es.upm.pproject.sokoban.model;

import java.security.InvalidParameterException;
import java.util.Map;

import es.upm.pproject.sokoban.interfaces.Square;

public class Box implements Square {

	private Position position;
	private Map<Position, Square> board;
		private GoalPosition goalPosition = null;

	public Box(Position position, Map<Position, Square> board) {
		this.position = position;
		this.board = board;
	}

	private boolean checkPosition(Position newPosition, char way) {
		Square square;
		if (newPosition == null) return false;

		if ((square = this.board.get(newPosition)) != null) {
			if (square instanceof GoalPosition) {
				goalPosition = (GoalPosition) square;
				board.remove(position);
				this.position = newPosition;
				board.put(position, this);
				return true;
			} else {
				return false;
			}
		}
		if (goalPosition != null) {
			board.put(position,goalPosition);
			goalPosition = null;
		}
		else {
			board.remove(position);
		}
		this.position = newPosition;
		board.put(position, this);
		return true;
	}

	public boolean move(char way) {
		Position newPosition;
		switch (way) {
			case 'N':
				newPosition = new Position(position.getX(), position.getY() - 1);
				break;
			case 'S':
				newPosition = new Position(position.getX(), position.getY() + 1);
				break;
			case 'E':
				newPosition = new Position(position.getX() + 1, position.getY());
				break;
			case 'W':
				newPosition = new Position(position.getX() - 1, position.getY());
				break;
			default:
				throw new InvalidParameterException("The warehouse man just can move N (north), S (south), E (east), W (west)");
		}
		return checkPosition(newPosition, way);
	}
	public Position getPosition() {
			return position;
		}
	public GoalPosition getGoalPosition(){
		return goalPosition;
	}
	public Map<Position, Square> getBoard() {
		return board;
	}
}
