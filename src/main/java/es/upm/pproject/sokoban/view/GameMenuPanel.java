package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameMenuPanel extends JPanel {
    GameController gameController;
    public GameMenuPanel(JPanel contentPane, GameController gameController) {
        this.gameController = gameController;

        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Exit");

        startButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.next(contentPane);
        });

        exitButton.addActionListener(e -> System.exit(1));

        add(startButton, BorderLayout.CENTER);
        add(exitButton, BorderLayout.CENTER);
    }
}
