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

    public HashMap<Position, Square> getBoard() {
        return board;
    }
    public int getRows() {
    	return rows;
    }
    public int getCols() {
    	return cols;
    }

    public Map<Position, Square> parse(String fileName) {
        String path = "./levels/" + fileName;
        File fd = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(fd))) {
            // Creating the map
            board = new HashMap<>();

            // Getting the level name
            String level = br.readLine();

            // Getting the dimensions of the board
            String[] dimensions = br.readLine().split(" ");
            rows = Integer.parseInt(dimensions[0]);
            cols = Integer.parseInt(dimensions[1]);

            // Getting the board itself
            String line;
            Position position;

            for (int i = rows - 1; i >= 0; --i) {
                line = br.readLine();
                for (int j = 0; j < line.length(); ++j) {
                    switch (line.charAt(j)) {
                        case ' ':
                            // Don't add to the map
                            break;
                        case '+':
                            position = new Position(j, i);
                            board.put(position, new Wall(position, board));
                            break;
                        case '*':
                            position = new Position(j, i);
                            board.put(position, new Box(position, board));
                            break;
                        case 'W':
                            position = new Position(j, i);
                            board.put(position, new WarehouseMan(position, board));
                            break;
                        case '#':
                            position = new Position(j, i);
                            board.put(position, new GoalPosition(position, board));
                            break;
                    }
                }
            }
            return board;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}