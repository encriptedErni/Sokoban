package es.upm.pproject.sokoban.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.swing.*;

import es.upm.pproject.sokoban.controller.GameController;
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
	private final transient Image box_win;
	private final transient Image goalPosition;
	private final transient Image warehouseMan;
	private final transient Image floor;
	private final GameFrame gameFrame;
	private final GameController controller;

	private Action moveUp;
	private Action moveDown;
	private Action moveLeft;
	private Action moveRight;


    public GamePanel(Map<Position,Square> board, int rows, int cols, GameFrame gameFrame,GameController controller) {
        this.board = board;
		this.rows = rows;
		this.cols = cols;
		this.gameFrame = gameFrame;
		this.controller = controller;
        this.wall = loadImage("./sprites/wall.png");
		this.box = loadImage("./sprites/box.png");
		this.box_win = loadImage("./sprites/box_win.png");
		this.goalPosition = loadImage("./sprites/goal-position.png");
		this.floor = loadImage("./sprites/floor.png");
		this.warehouseMan = loadImage("./sprites/warehouse-man.png");

		setActions();
		setKeys();
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
			if(((Box) o).getGoalPosition() != null) {
				return this.box_win;
			}
			return this.box;
		} else if (o instanceof GoalPosition) {
			return this.goalPosition;
		} else if (o instanceof WarehouseMan) {
			return this.warehouseMan;
		}
		return null;
	}

	private void setKeys() {
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUpArrow");
		getActionMap().put("moveUpArrow", moveUp);
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "moveUpW");
		getActionMap().put("moveUpW", moveUp);

		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDownArrow");
		getActionMap().put("moveDownArrow", moveDown);
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "moveDownS");
		getActionMap().put("moveDownS", moveDown);

		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeftArrow");
		getActionMap().put("moveLeftArrow", moveLeft);
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "moveLeftA");
		getActionMap().put("moveLeftA", moveLeft);

		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRightArrow");
		getActionMap().put("moveRightArrow", moveRight);
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "moveRightD");
		getActionMap().put("moveRightD", moveRight);
	}

	private void setActions() {
		this.moveUp = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveUp();
				repaint();
			}
		};

		this.moveDown = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveDown();
				repaint();
			}
		};

		this.moveLeft = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveLeft();
				repaint();
			}
		};

		this.moveRight = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveRight();
				repaint();
			}
		};
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
