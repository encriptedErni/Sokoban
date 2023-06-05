package es.upm.pproject.sokoban.model;

import javax.swing.*;

public class LevelMovementCounter extends JLabel {
    private int movements;

    public LevelMovementCounter() {
        super("Level Score: 0");
        this.movements = 0;
    }

    public int getMovementCount() {
        return this.movements;
    }

    public void resetMovementCount() {
        this.movements = 0;
        setText("Level Score: " + this.movements);
    }

    public void incrementMovementCount() {
        this.movements++;
        setText("Level Score: " + this.movements);
    }

    public void decrementMovementCount() {
        this.movements--;
        setText("Level Score: " + this.movements);
    }
}
