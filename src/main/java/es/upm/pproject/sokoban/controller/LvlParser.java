package es.upm.pproject.sokoban.controller;

import es.upm.pproject.sokoban.interfaces.Square;
import es.upm.pproject.sokoban.model.Position;
import es.upm.pproject.sokoban.model.Wall;
import es.upm.pproject.sokoban.model.WarehouseMan;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class GameController {
    HashMap<Position, Square> board;

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
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);

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
                            // board.put(position, new Box());
                            break;
                        case 'W':
                            position = new Position(j, i);
                            board.put(position, new WarehouseMan(position, board));
                            break;
                        case '#':
                            position = new Position(j, i);
                            // board.put(position, new GoalPosition());
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

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.parse("level1.txt");
    }
}