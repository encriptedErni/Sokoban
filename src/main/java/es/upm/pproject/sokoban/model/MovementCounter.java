package es.upm.pproject.sokoban.model;

import javax.swing.*;

public abstract class MovementCounter extends JLabel {
    protected int movements;

    public MovementCounter(String label) {
        super(label + " Score: 0");
        this.movements = 0;
    }

    public int getMovementCount() {
        return this.movements;
    }

    public void setMovementCount(int movements){
        this.movements = movements;
        setText(getLabel() + " Score: " + this.movements);
    }

    public void resetMovementCount() {
        this.movements = 0;
        setText(getLabel() + " Score: " + this.movements);
    }

    public void incrementMovementCount() {
        this.movements++;
        setText(getLabel() + " Score: " + this.movements);
    }

    public void decrementMovementCount() {
        this.movements--;
        setText(getLabel() + " Score: " + this.movements);
    }
    protected abstract String getLabel();
}

