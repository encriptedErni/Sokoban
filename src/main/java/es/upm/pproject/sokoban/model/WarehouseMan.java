package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.interfaces.Square;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class WarehouseMan extends Square {
    private int movements = 0;
    private Position position;
    private HashMap board;

    public WarehouseMan(Position position, HashMap<Position, Square> board) {
        this.position = position;
        this.board = board;
    }

    private boolean checkPosition(Position newPosition, char way) {
        Square square;
        if (newPosition == null) return false;
        if (board.containsKey(newPosition)) {
            square = (Square) board.get(newPosition);
            if (!square.move(way)) {
                return false;
            }
        }
        board.remove(position);
        this.position = newPosition;
        movements++;
        board.put(position, this);
        return true;
    }

    public boolean move(char way) {
        Position newPosition = null;
        switch (way) {
            case 'N':
                newPosition = new Position(position.getX(), position.getY() + 1);
                break;
            case 'S':
                newPosition = new Position(position.getX(), position.getY() - 1);
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

    public int getMovements() {
        return movements;
    }
}
