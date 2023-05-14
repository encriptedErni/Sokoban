package es.upm.pproject.sokoban.controller;


import es.upm.pproject.sokoban.interfaces.Square;
import es.upm.pproject.sokoban.model.Position;
import es.upm.pproject.sokoban.model.Wall;
import es.upm.pproject.sokoban.model.WarehouseMan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Class to test map and element interactions")
public class GameControllerTest {
    private HashMap<Position, Square> board;

    @BeforeEach
    void initMap() {
        GameController gameController = new GameController();
        board = (HashMap<Position, Square>) gameController.parse("level1.txt");

    }

    @Test
    void boardParse() {
        assertNotNull(board);
        assertNotNull(board.get(new Position(0, 0)));
    }

}
