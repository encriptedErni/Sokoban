package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.GameController;

import javax.swing.*;


public class Main {
	public static void main(String[] args) {
        try {
            GameController game = new GameController();
            game.parse("level1.txt");
            SwingUtilities.invokeLater(() -> new GameFrame(game));
            // try to invoke parse level 2
        } catch (Exception e) {
            System.err.println("Something went wrong!" + e);
        }
    }
}
