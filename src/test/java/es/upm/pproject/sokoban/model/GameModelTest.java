package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.interfaces.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameModelTest {
    private HashMap<Position, Square> board;
    @BeforeEach
    void initMap() {
        GameController gameController = new GameController();
        board = (HashMap<Position, Square>) gameController.parse("level1.txt");

    }

    @Nested
    class WarehouseManMoves {
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
    @Test
    void WarehouseManMovements () {
        WarehouseMan warehouseMan = (WarehouseMan) board.get(new Position(2, 3));
        assertEquals(warehouseMan.getMovements(), 0);
        warehouseMan.move('W');
        assertEquals(warehouseMan.getMovements(), 0);
        warehouseMan.move('N');
        assertEquals(warehouseMan.getMovements(), 1);
        warehouseMan.move('W');
        assertEquals(warehouseMan.getMovements(), 2);
        warehouseMan.move('W');
        assertEquals(warehouseMan.getMovements(), 2);
        warehouseMan.move('N');
        warehouseMan.move('E');
        warehouseMan.move('S');
        assertEquals(warehouseMan.getMovements(), 5);

    }
}
