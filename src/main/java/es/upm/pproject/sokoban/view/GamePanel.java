package es.upm.pproject.sokoban.view;


import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;

import es.upm.pproject.sokoban.model.*;
import es.upm.pproject.sokoban.model.Box;

import javax.imageio.ImageIO;


public class GamePanel extends JPanel{
	HashMap board;
	Image Wall, Box, GoalPosition, WarehouseMan, Floor;
	
	private static final int CELL_SIZE = 50;
    public GamePanel(HashMap board) {
        this.board = board;
        Wall = loadImage("./sprites/wall.png"); 
		Box = loadImage("./sprites/box.png");
		GoalPosition = loadImage("./sprites/goal-position.png");
		Floor = loadImage("./sprites/floor.png");
		WarehouseMan = loadImage("./sprites//warehouse-man.png");
    }
    
    private Image loadImage(String path) {
		Image img = null;
		String imagePath = path; // Replace with the actual path to your image
        
        try {
            // Load the image from the file
            File imageFile = new File(imagePath);
            img = ImageIO.read(imageFile);
            
        } catch (IOException e) {
            System.out.println("Failed to load the image: " + e.getMessage());
        }
		return img;
	}
    
    
    private Image classToImage(Object o) {
		Image img = null;
		
		
		if(o.getClass().equals(new Wall(null, null).getClass())) {
			System.out.println("ENTREEE");
			img = Wall;
		}
		
		else if(o.getClass().equals(new Box(null, null).getClass())) {
			img = Box;
		}
		
		else if(o.getClass().equals(new GoalPosition(null, null).getClass())) {
			img = GoalPosition;
		}
		
		else if(o.getClass().equals(new WarehouseMan(null, null).getClass())) {
			img = WarehouseMan;
		}
		
		//nunca va a ser floor
		
		return img;
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        for (int i = 0; i < 10; i++) {
        	for(int j = 0; j < 10; j++) {
        		System.out.println(i + "......." + j);
        		g.drawImage(Floor, j*CELL_SIZE, i*CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
        		
        		Position p = new Position(j, i);
		        if (board.containsKey(p)) {
		        	g.drawImage(classToImage(board.get(p)), j*CELL_SIZE, i*CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
		        }
        	}
        }
    }
}
