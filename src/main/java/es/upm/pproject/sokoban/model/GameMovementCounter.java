package es.upm.pproject.sokoban.model;

import javax.swing.*;

public class GameMovementCounter extends JLabel {
    private int movements;

    public GameMovementCounter() {
        super("Game Score: 0");
        this.movements = 0;
    }

    public int getMovementCount() {
        return this.movements;
    }

    public void resetMovementCount() {
        this.movements = 0;
        setText("Game Score: " + this.movements);
    }

    public void incrementMovementCount() {
        this.movements++;
        setText("Game Score: " + this.movements);
    }

    public void decrementMovementCount() {
        this.movements--;
        setText("Game Score: " + this.movements);
    }
}
