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
        assertNotNull(board.get(new Position(0,0)));
    }

    @Nested
    class WarehouseManWithWall {
        private WarehouseMan warehouseMan;
        private Wall wall;

        @BeforeEach
        void initWarehouseManAndWall() {
            warehouseMan = (WarehouseMan) board.get(new Position(2, 3));
            wall = (Wall) board.get(new Position(1, 3));
        }

        @Test
        void WarehouseManMoveToEmptyPosition() {
            Position PreviousPosition = warehouseMan.getPosition();
            assertTrue(warehouseMan.move('N'));
            assertNotNull(board.get(new Position(2, 4)));
            WarehouseMan warehouseManMoved = (WarehouseMan) board.get(new Position(2, 4));
            Position NewPosition = warehouseManMoved.getPosition();
            assertEquals(PreviousPosition.getX(), NewPosition.getX());
            assertNotEquals(PreviousPosition.getY(), NewPosition.getY());

        }

        @Test
        void WarehouseManMoveToWall() {
            Position PreviousPositionWarehouseMan = warehouseMan.getPosition();
            Position PreviousPositionWall = wall.getPosition();
            assertFalse(warehouseMan.move('W'));
            assertNotNull(board.get(new Position(2, 3)));
            assertNotNull(board.get(new Position(1, 3)));
            WarehouseMan warehouseManNotMoved = (WarehouseMan) board.get(new Position(2, 3));
            Wall wallNotMoved = (Wall) board.get(new Position(1, 3));
            Position NewPositionWarehouseMan = warehouseManNotMoved.getPosition();
            assertEquals(PreviousPositionWarehouseMan.getX(), NewPositionWarehouseMan.getX());
            assertEquals(PreviousPositionWarehouseMan.getY(), NewPositionWarehouseMan.getY());
            Position NewPositionWall = wallNotMoved.getPosition();
            assertEquals(PreviousPositionWall.getX(), NewPositionWall.getX());
            assertEquals(PreviousPositionWall.getY(), NewPositionWall.getY());
        }

    }
}
