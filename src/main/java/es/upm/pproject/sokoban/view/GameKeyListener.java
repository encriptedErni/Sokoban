package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.GameController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    private GameController controller;

    public GameKeyListener(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (isUp(keyCode)) {
            // controller.moveUp();
        } else if (isDown(keyCode)) {
            // controller.moveDown();
        } else if (isRight(keyCode)) {
            // controller.moveRight();
        } else if (isLeft(keyCode)) {
            // controller.moveLeft();
        } else if (isPause(keyCode)) {
            // controller.pause();
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
    public void keyReleased(KeyEvent e){}
    @Override
    public void keyTyped(KeyEvent e){}
}
