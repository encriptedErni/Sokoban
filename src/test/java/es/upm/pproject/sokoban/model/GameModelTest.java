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
    @Nested
    class BoxTests {
        private WarehouseMan warehouseMan;
        private Box upBox;
        private Box rightBox;

        @BeforeEach
        void initWarehouseManAndWall() {
            warehouseMan = (WarehouseMan) board.get(new Position(2, 4));
            upBox = (Box) board.get(new Position(2, 3));
            rightBox = (Box) board.get(new Position(3, 4));
        }

        @Test
        void BoxCanMoveToEmptySpace() {
            Position PreviousManPos = warehouseMan.getPosition();
            Position PreviousBoxPos = upBox.getPosition();
            assertTrue(warehouseMan.move('N',0));
            assertNotNull(board.get(new Position(2, 3)));
            assertNotNull(board.get(new Position(2, 2)));
            WarehouseMan warehouseManMoved = (WarehouseMan) board.get(new Position(2, 3));
            Box upBoxMoved = (Box) board.get(new Position(2, 2));
            Position NewManPos = warehouseManMoved.getPosition();
            Position NewBoxPos = upBoxMoved.getPosition();
            assertEquals(NewManPos.getX(), PreviousManPos.getX());
            assertEquals(NewManPos.getY(), PreviousManPos.getY() - 1);
            assertEquals(NewBoxPos.getX(), PreviousBoxPos.getX());
            assertEquals(NewBoxPos.getY(), PreviousBoxPos.getY() - 1);
            /* El mismo test
            assertTrue(warehouseMan.move('N',0));
            assertEquals(new Position(2,2), upBox.getPosition());
             */
        }
        @Test
        void BoxAgainstWallDontMove() {
            assertFalse(warehouseMan.move('E',0));
        }
        @Test
        void BoxAgainstBoxDontMove() {
            assertTrue(warehouseMan.move('N',0));
            assertTrue(warehouseMan.move('W',1));
            assertTrue(warehouseMan.move('N',2));
            assertTrue(warehouseMan.move('E',3));
            assertTrue(warehouseMan.move('N',4));
            assertTrue(warehouseMan.move('E',5));
            assertTrue(warehouseMan.move('S',6));
            assertFalse(warehouseMan.move('S',7));
            assertEquals(new Position(rightBox.getPosition().getX(), rightBox.getPosition().getY()-2), warehouseMan.getPosition());
            assertEquals(new Position(rightBox.getPosition().getX(), rightBox.getPosition().getY()-1), upBox.getPosition());
            assertNotNull(board.get(rightBox.getPosition()));
        }
        @Test
        void BoxGetsGoal() {
            assertTrue(warehouseMan.move('N',0));
            assertTrue(warehouseMan.move('W',1));
            assertTrue(warehouseMan.move('N',2));
            assertTrue(warehouseMan.move('E',3));
            assertTrue(warehouseMan.move('E',4));
            assertTrue(warehouseMan.move('E',5));
            assertNotNull(upBox.getGoalPosition());
        }
    }
}
