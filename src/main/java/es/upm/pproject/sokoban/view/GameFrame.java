package es.upm.pproject.sokoban.view;

import javax.swing.*;

import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.interfaces.Square;
import es.upm.pproject.sokoban.model.*;
import es.upm.pproject.sokoban.model.Box;

import java.util.HashMap;

public class GameFrame  extends JFrame{
    public GameFrame(GameController gameController) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sokoban");
        GamePanel boardPanel = new GamePanel(gameController.getBoard(), gameController.getRows(), gameController.getCols());
        add(boardPanel);
        addKeyListener(new GameKeyListener(gameController));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(50 * gameController.getCols(),50 * gameController.getRows());
    }
}

  
   


