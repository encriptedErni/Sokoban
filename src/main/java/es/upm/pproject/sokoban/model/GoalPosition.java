package es.upm.pproject.sokoban.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import es.upm.pproject.sokoban.interfaces.Square;

public class GoalPosition implements Square {

    private Position position;
    private Map<Position, Square> board;
    
    public GoalPosition(Position position, Map<Position, Square> board) {
        this.position = position;
        this.board = board;
    }

    public boolean isLevelWon(Box box){
        Position boxPosition = box.getPosition();
        return position.equals(boxPosition);
    }
    public boolean move(char way) {
        return true;
    }

    public Position getPosition() {
        return position;
    }

    public Map<Position, Square> getBoard() {
        return board;
    }

    public void setBoard(Map<Position, Square> board) {
        this.board = board;
    }
}
