package es.upm.pproject.sokoban.view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.model.Position;

public class LevelView extends Component {
	GameController game;
	String lvlname;
	Image Wall, Box, GoalPosition, WarehouseMan, Floor;
	
	public LevelView(String lvlname) {
		Wall = loadImage("./sprites/wall.png"); 
		Box = loadImage("./sprites/box.png");
		GoalPosition = loadImage("./sprites/goal-position.png");
		Floor = loadImage("./sprites/floor.png");
		WarehouseMan = loadImage("./sprites//warehouse-man.png");
		this.lvlname = lvlname;
	}
	
	// carga los graficos (cambiar formato del path)
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
		
		if(o.getClass().getName().equals("Wall")) {
			img = Wall;
		}
		
		else if(o.getClass().getName().equals("Box")) {
			img = Box;
		}
		
		else if(o.getClass().getName().equals("GoalPosition")) {
			img = GoalPosition;
		}
		
		else if(o.getClass().getName().equals("WarehouseMan")) {
			img = WarehouseMan;
		}
		
		//nunca va a ser floor
		
		return img;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D draw = (Graphics2D) g;
		
		int width = 50;
        int height = 50;

        draw.clearRect(0, 0, width, height);

        game.parse(lvlname);
        int cellWidth = width/game.getCols();
        int cellHeight = height/game.getRows();
        
        for(int i = 0; i < game.getCols(); i++) {
        	for(int j = 0; j < game.getRows(); j++) {
        		System.out.print("Entramos");
        		int x = i * cellWidth;
        		int y = j * cellHeight;
        		
        		draw.drawImage(Floor, x, y, cellWidth, cellHeight, null);
        		
        		if(game.board.containsKey(new Position(j, i))) 
        			draw.drawImage(classToImage(game.board.getClass()), x, y, cellWidth, cellHeight, null);
        	}
        }
		
	}
	
	
}
