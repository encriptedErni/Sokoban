package es.upm.pproject.sokoban.view;

import javax.swing.*;

import es.upm.pproject.sokoban.controller.GameController;

public class GameFrame  extends JFrame{
    public GameFrame(GameController gameController) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sokoban - " + gameController.getLevelName());
        GamePanel boardPanel = new GamePanel(gameController.getBoard(),gameController.getRows(),gameController.getCols(),this);
        add(boardPanel);
        addKeyListener(new GameKeyListener(gameController, boardPanel));
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(50 * gameController.getCols(),50 * gameController.getRows());
        pack();
    }
}

  
   


