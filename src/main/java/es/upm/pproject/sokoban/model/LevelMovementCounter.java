package es.upm.pproject.sokoban.model;

import javax.swing.*;

public class LevelMovementCounter extends MovementCounter {
    public LevelMovementCounter() {
        super("Level");
    }

    @Override
    protected String getLabel() {
        return "Level";
    }
}
