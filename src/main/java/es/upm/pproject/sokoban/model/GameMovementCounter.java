package es.upm.pproject.sokoban.model;

import javax.swing.*;

public class GameMovementCounter extends MovementCounter {
    public GameMovementCounter() {
        super("Game");
    }

    @Override
    protected String getLabel() {
        return "Game";
    }
}
