package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.interfaces.Square;

import java.util.HashMap;

public class WarehouseMan extends Square {
    private Position position;
    private static HashMap board = new HashMap<Position, Square>();

    public WarehouseMan(Position position, HashMap<Position, Square> board) {
        this.position = position;
        WarehouseMan.board = board;
    }

    private boolean checkPosition(Position newPosition, char way) {
        Square square;
        if (newPosition==null) return false;
        if (board.containsKey(newPosition)) {
            square = (Square) board.get(newPosition);
            if (!square.move(way)) {
                return false;
            }
        }
        board.remove(position);
        this.position = newPosition;
        board.put(position, this);
        return true;
    }

    public boolean move(char way) {
        Position newPosition = null;
        switch (way) {
            case 'N':
                newPosition = new Position(position.getX() + 1, position.getY());
                break;
            case 'S':
                newPosition = new Position(position.getX() - 1, position.getY());
                break;
            case 'E':
                newPosition = new Position(position.getX(), position.getY() + 1);
                break;
            case 'W':
                newPosition = new Position(position.getX(), position.getY() - 1);
                break;
        }
        return checkPosition(newPosition, way);
    }
}
