package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.interfaces.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameModelTest {
    private Map<Position, Square> board;
    @BeforeEach
    void initMap() {
        GameController gameController = new GameController();
        board = gameController.parse(1);

    }

    @Nested
    class WarehouseManMoves {
        private WarehouseMan warehouseMan;
        private Wall wall;

        @BeforeEach
        void initWarehouseManAndWall() {
            warehouseMan = (WarehouseMan) board.get(new Position(2, 4));
            wall = (Wall) board.get(new Position(1, 4));
        }

        @Test
        void WarehouseManMoveToEmptyPosition() {
            Position PreviousPosition = warehouseMan.getPosition();
            assertTrue(warehouseMan.move('N',7));
            assertNotNull(board.get(new Position(2, 3)));
            WarehouseMan warehouseManMoved = (WarehouseMan) board.get(new Position(2, 3));
            Position NewPosition = warehouseManMoved.getPosition();
            assertEquals(PreviousPosition.getX(), NewPosition.getX());
            assertNotEquals(PreviousPosition.getY(), NewPosition.getY());

        }

        @Test
        void WarehouseManMoveToWall() {
            Position PreviousPositionWarehouseMan = warehouseMan.getPosition();
            Position PreviousPositionWall = wall.getPosition();
            assertFalse(warehouseMan.move('W',2));
            assertNotNull(board.get(new Position(2, 4)));
            assertNotNull(board.get(new Position(1, 4)));
            WarehouseMan warehouseManNotMoved = (WarehouseMan) board.get(new Position(2, 4));
            Wall wallNotMoved = (Wall) board.get(new Position(1, 4));
            Position NewPositionWarehouseMan = warehouseManNotMoved.getPosition();
            assertEquals(PreviousPositionWarehouseMan.getX(), NewPositionWarehouseMan.getX());
            assertEquals(PreviousPositionWarehouseMan.getY(), NewPositionWarehouseMan.getY());
            Position NewPositionWall = wallNotMoved.getPosition();
            assertEquals(PreviousPositionWall.getX(), NewPositionWall.getX());
            assertEquals(PreviousPositionWall.getY(), NewPositionWall.getY());
        }

    }
    /*@Test
    void WarehouseManMovements () {
        WarehouseMan warehouseMan = (WarehouseMan) board.get(new Position(2, 4));
        assertEquals(0, warehouseMan.getMovements());
        warehouseMan.move('W',0);
        assertEquals(0, warehouseMan.getMovements());
        warehouseMan.move('N',0);
        assertEquals(1, warehouseMan.getMovements());
        warehouseMan.move('W',0);
        assertEquals(2, warehouseMan.getMovements());
        warehouseMan.move('W',0);
        assertEquals(2, warehouseMan.getMovements());
        warehouseMan.move('N',0);
        warehouseMan.move('E',0);
        warehouseMan.move('S',0);
        assertEquals(5, warehouseMan.getMovements());

    }*/
}
