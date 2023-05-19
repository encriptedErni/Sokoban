package es.upm.pproject.sokoban.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;

import es.upm.pproject.sokoban.interfaces.Square;
import es.upm.pproject.sokoban.model.*;
import es.upm.pproject.sokoban.model.Box;

import javax.imageio.ImageIO;


public class GamePanel extends JPanel {
	private static final int CELL_SIZE = 50;
	private final int rows, cols;
	private final HashMap<Position, Square> board;
	private final Image wall, box, goalPosition, warehouseMan, floor;
    public GamePanel(HashMap<Position, Square> board, int rows, int cols) {
        this.board = board;
		this.rows = rows;
		this.cols = cols;
        wall = loadImage("./sprites/wall.png");
		box = loadImage("./sprites/box.png");
		goalPosition = loadImage("./sprites/goal-position.png");
		floor = loadImage("./sprites/floor.png");
		warehouseMan = loadImage("./sprites/warehouse-man.png");
    }
    
    private Image loadImage(String imagePath) {
		Image img = null;
        try {
            File imageFile = new File(imagePath);
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("Failed to load the image: " + e.getMessage());
        }
		return img;
	}

    private Image classToImage(Object o) {
		Image img = null;

		if (o instanceof Wall) {
			img = wall;
		} else if (o instanceof Box) {
			img = box;
		} else if (o instanceof GoalPosition) {
			img = goalPosition;
		} else if (o instanceof WarehouseMan) {
			img = warehouseMan;
		}
		return img;
	}

    @Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < cols; ++i)
			for (int j = 0; j < rows; ++j)
				g.drawImage(floor, j * CELL_SIZE, i * CELL_SIZE, null, null);

		board.forEach((pos, square) -> g.drawImage(classToImage(square), pos.getX() * CELL_SIZE,
				pos.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE, null));
    }
}
