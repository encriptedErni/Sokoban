package es.upm.pproject.sokoban.view;

import javax.swing.*;

import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.model.*;
import es.upm.pproject.sokoban.model.Box;

import java.util.HashMap;

public class GameFrame  extends JFrame{
	
    private HashMap board;
    public GameFrame(HashMap board) {
    	this.board = board;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sokoban");

        GamePanel boardPanel = new GamePanel(board);
        add(boardPanel);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
        setSize(500,500);
    }
    
    // main prueba
    
    public static void main(String[] args) {
    	HashMap board = new HashMap();
    	int j = 0;
    	
    	Position p = new Position(5, 5);
    	board.put(p, new WarehouseMan(p, board));
    	p = new Position(3, 3);
    	board.put(p, new GoalPosition(p, board));
    	p = new Position(2, 3);
    	board.put(p, new Box(p, board));
    	for(int i = 0; i<10; i++) {
    		p = new Position(j, i);
    		board.put(p, new Wall(p, board));
    	}
    	
    	GameFrame g = new GameFrame(board);
    }
    
}

  
   


