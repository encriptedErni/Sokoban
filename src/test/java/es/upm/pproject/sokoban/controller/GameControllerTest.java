package es.upm.pproject.sokoban.controller;


import es.upm.pproject.sokoban.interfaces.Square;
import es.upm.pproject.sokoban.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Class to test map and element interactions")
class GameControllerTest {
    private Map<Position, Square> board;

    @BeforeEach
    void initMap() {
        GameController gameController = new GameController();
        board = gameController.parse("level1.txt");

    }

    @Test
    void boardParse() {
        assertNotNull(board);
        assertNotNull(board.get(new Position(0, 0)));
    }

}
