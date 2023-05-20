package es.upm.pproject.sokoban.controller;

import es.upm.pproject.sokoban.interfaces.Square;
import es.upm.pproject.sokoban.model.Box;
import es.upm.pproject.sokoban.model.GoalPosition;
import es.upm.pproject.sokoban.model.Position;
import es.upm.pproject.sokoban.model.Wall;
import es.upm.pproject.sokoban.model.WarehouseMan;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class GameController {
    private HashMap<Position, Square> board;
    private int rows;
    private int cols;
    private String levelName;
    private WarehouseMan warehouseMan;
    public Map<Position, Square> getBoard() {
        return board;
    }
    public int getRows() {
    	return rows;
    }
    public int getCols() {
    	return cols;
    }

    public String getLevelName() {
        return levelName;
    }

    public Map<Position, Square> parse(String fileName) {
        String path = "./levels/" + fileName;
        File fd = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(fd))) {
            // Creating the map
            this.board = new HashMap<>();

            // Getting the level name
            this.levelName = br.readLine();

            // Getting the dimensions of the board
            String[] dimensions = br.readLine().split(" ");
            this.rows = Integer.parseInt(dimensions[0]);
            this.cols = Integer.parseInt(dimensions[1]);

            // Getting the board itself
            String line;
            Position position;

            for (int i = 0; i < rows; ++i) {
                line = br.readLine();
                for (int j = 0; j < line.length(); ++j) {
                    switch (line.charAt(j)) {
                        case ' ':
                            // Don't add to the map
                            break;
                        case '+':
                            position = new Position(j, i);
                            this.board.put(position, new Wall(position));
                            break;
                        case '*':
                            position = new Position(j, i);
                            this.board.put(position, new Box(position, this.board));
                            break;
                        case 'W':
                            position = new Position(j, i);
                            this.warehouseMan = new WarehouseMan(position, this.board);
                            this.board.put(position, warehouseMan);
                            break;
                        case '#':
                            position = new Position(j, i);
                            this.board.put(position, new GoalPosition(position, this.board));
                            break;
                        default:
                            break;
                    }
                }
            }
            return this.board;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    // Moving character through board
    public void moveUp() {
        this.warehouseMan.move('N');
    }
    public void moveDown() {
        this.warehouseMan.move('S');
    }
    public void moveLeft(){
        this.warehouseMan.move('W');
    }
    public void moveRight() {
        this.warehouseMan.move('E');
    }

    // implement later
    public void pause() {
        // TODO: the event handler of pausing the game. SPRINT-2
    }
}
