package es.upm.pproject.sokoban.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import es.upm.pproject.sokoban.interfaces.Square;

public class GoalPosition implements Square {

    private Position position;
    
    public GoalPosition(Position position, Map<Position, Square> board) {
        this.position = position;
    }

    public boolean move(char way, int turn) {
        return true;
    }

    public void unmove(char way, int turn) { return;}

    public Position getPosition() {
        return position;
    }

}
