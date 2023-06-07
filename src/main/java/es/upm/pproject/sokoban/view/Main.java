package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.GameController;

import javax.swing.*;


public class Main {
	public static void main(String[] args) {
        try {
            GameController game = new GameController();
            game.parse(1);
            SwingUtilities.invokeLater(() -> new GameFrame(game));
        } catch (Exception e) {
            System.err.println("Something went wrong!" + e);
        }
    }
}
