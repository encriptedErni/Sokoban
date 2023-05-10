package es.upm.pproject.sokoban.controller;
import es.upm.pproject.sokoban.interfaces.Square;
import es.upm.pproject.sokoban.model.Position;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class GameController {
    public static Map<Position,Square> parse(String fileName) {
        String path = "./levels/" + fileName;
        File fd = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(fd))) {
            // Creating the map
            HashMap<Position,Square> board = new HashMap<>();

            // Getting the level name
            String level = br.readLine();

            // Getting the dimensions of the board
            String[] dimensions = br.readLine().split(" ");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);

            // Getting the board itself
            String line;
            for (int i = 0; i < rows; ++i) {
                line = br.readLine();
                for (int j = 0; j < line.length(); ++j) {
                    switch(line.charAt(j)) {
                        case ' ':
                            // Don't add to the map
                            break;
                        case '+':
                            // board.put(new Position(i, j), new Wall());
                            break;
                        case '*':
                            // board.put(new Position(i, j), new Box());
                            break;
                        case 'W':
                            // board.put(new Position(i, j), new WarehouseMan());
                            break;
                        case '#':
                            // board.put(new Position(i, j), new GoalPosition());
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
        parse("level1.txt");
    }
}