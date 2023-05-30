package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.model.GoalPosition;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    private final GameController controller;
    private final GamePanel boardPanel;
    public GameKeyListener(GameController controller, GamePanel boardPanel) {
        this.controller = controller;
        this.boardPanel = boardPanel;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (isUp(keyCode)) {
            this.controller.moveUp();
            this.boardPanel.repaint();
        } else if (isDown(keyCode)) {
            this.controller.moveDown();
            this.boardPanel.repaint();
        } else if (isRight(keyCode)) {
            this.controller.moveRight();
            this.boardPanel.repaint();
        } else if (isLeft(keyCode)) {
            this.controller.moveLeft();
            this.boardPanel.repaint();
        } else if (isPause(keyCode)) {
            this.controller.pause();
            this.boardPanel.repaint();
        }
    }

    private boolean isUp(int keyCode) {
        return keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W;
    }
    private boolean isDown(int keyCode) {
        return keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S;
    }
    private boolean isRight(int keyCode) {
        return keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D;
    }
    private boolean isLeft(int keyCode) {
        return keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A;
    }
    private boolean isPause(int keyCode) {
        return keyCode == KeyEvent.VK_ESCAPE;
    }

    @Override
    public void keyReleased(KeyEvent e){
        // Not needed for our game, but necessary when implementing the interface
    }
    @Override
    public void keyTyped(KeyEvent e){
        // Not needed for our game, but necessary when implementing the interface
    }
}
