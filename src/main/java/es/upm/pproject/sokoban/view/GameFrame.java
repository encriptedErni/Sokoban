package es.upm.pproject.sokoban.view;

import javax.swing.*;

import es.upm.pproject.sokoban.controller.GameController;

import java.awt.*;
import java.util.HashMap;

public class GameFrame  extends JFrame{
	
	private static final int CELL_SIZE = 50;
    private HashMap board;
    public GameFrame() {
    	 this.board = board;

         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setTitle("Sokoban");

         GamePanel boardPanel = new GamePanel();
         add(boardPanel);

         pack();
         setLocationRelativeTo(null); // Center the frame on the screen
         setVisible(true);
         setSize(500,500);
    }
    
    public static void main(String[] args) {
    	GameFrame g = new GameFrame();
    }
    
}

  
   


