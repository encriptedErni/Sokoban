package es.upm.pproject.sokoban.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.swing.JPanel;


import es.upm.pproject.sokoban.interfaces.Square;
import es.upm.pproject.sokoban.model.*;
import es.upm.pproject.sokoban.model.Box;

import javax.imageio.ImageIO;


public class GamePanel extends JPanel {
	private final int rows;
	private final int cols;
	private final Map<Position, Square> board;
	private final transient Image wall;
	private final transient Image box;
	private final transient Image goalPosition;
	private final transient Image warehouseMan;
	private final transient Image floor;
	private final GameFrame gameFrame;
    public GamePanel(Map<Position,Square> board, int rows, int cols, GameFrame gameFrame) {
        this.board = board;
		this.rows = rows;
		this.cols = cols;
		this.gameFrame = gameFrame;
        this.wall = loadImage("./sprites/wall.png");
		this.box = loadImage("./sprites/box.png");
		this.goalPosition = loadImage("./sprites/goal-position.png");
		this.floor = loadImage("./sprites/floor.png");
		this.warehouseMan = loadImage("./sprites/warehouse-man.png");
    }

    private Image loadImage(String imagePath) {
		Image img = null;
        try {
            File imageFile = new File(imagePath);
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Failed to load the image: " + e.getMessage());
        }
		return img;
	}

    private Image classToImage(Object o) {

		if (o instanceof Wall) {
			return this.wall;
		} else if (o instanceof Box) {
			return this.box;
		} else if (o instanceof GoalPosition) {
			return this.goalPosition;
		} else if (o instanceof WarehouseMan) {
			return this.warehouseMan;
		}
		return null;
	}



    @Override
    protected void paintComponent(Graphics g) {
		int cellHeight = gameFrame.getHeight()/rows;
		int cellWidth = gameFrame.getWidth()/cols;
		super.paintComponent(g);
		for (int i = 0; i < rows; ++i)
			for (int j = 0; j < cols; ++j)
				g.drawImage(floor, j * cellWidth, i * cellHeight, cellWidth, cellHeight,null);
		board.forEach((pos, square) -> g.drawImage(classToImage(square), pos.getX() * cellWidth,
				pos.getY() * cellHeight, cellWidth, cellHeight, null));
    }
}
