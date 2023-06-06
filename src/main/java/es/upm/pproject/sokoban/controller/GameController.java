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
import java.util.Stack;

public class GameController {
    private static final int MAX_LEVELS = 3;
    private final HashMap<Position, Square> board = new HashMap<>();
    private Stack<Character> movements = new Stack<Character>();
    private int rows;
    private int cols;
    private String levelName;
    private WarehouseMan warehouseMan;
    private Box box;
    private GoalPosition goalPosition;
    private int actualLevel;

    public GameController() {
        this.actualLevel = 1;
    }

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

    public Map<Position, Square> parse(int levelNumber) {
        String path = "./levels/level" + levelNumber + ".txt";
        File fd = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(fd))) {
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
                            this.box = new Box(position, this.board);
                            this.board.put(position, box);
                            break;
                        case 'W':
                            position = new Position(j, i);
                            this.warehouseMan = new WarehouseMan(position, this.board);
                            this.board.put(position, warehouseMan);
                            break;
                        case '#':
                            position = new Position(j, i);
                            this.goalPosition = new GoalPosition(position, this.board);
                            this.board.put(position, goalPosition);
                            break;
                        default:
                            break;
                    }
                }
            }
            return this.board;
        } catch (IOException e) {
            System.err.println("IOException");
        }
        return new HashMap<>();
    }

    // Moving character through board
    public void moveUp(int turn) {
        if (this.warehouseMan.move('N', turn)) {
            movements.push('N');
        }
    }

    public void moveDown(int turn) {
        if (this.warehouseMan.move('S', turn)) {
            movements.push('S');
        }
    }

    public void moveLeft(int turn) {
        if (this.warehouseMan.move('W', turn)) {
            movements.push('W');
        }
    }

    public void moveRight(int turn) {
        if (this.warehouseMan.move('E', turn)) {
            movements.push('E');
        }
    }


    // implement later
    public void pause() {
        // TODO: the event handler of pausing the game. SPRINT-2
    }

    public void startNewGame() {
        this.board.clear();
        this.actualLevel = 1;
        parse(this.actualLevel);
    }

    public void restartGame() {
        this.board.clear();
        parse(this.actualLevel);
    }

    public boolean undoMovement(int turn) {
        if (movements.empty()) return false;
        Character movement = movements.pop();
        this.warehouseMan.unmove(movement, turn);
        return true;
    }

    public void nextLevel() {
        this.board.clear();
        this.actualLevel++;
        movements.clear();
        if (actualLevel == MAX_LEVELS) {
            // TODO: Print congrats message
        } else {
            parse(this.actualLevel);
        }
    }

    public void saveGame() {
        // TODO: Implement the logic of saving a game
    }

    public void openSavedGame() {
        // TODO: Implement the logic of opening a saved game
    }

    public void exitGame() {
        System.exit(1);
    }
}
