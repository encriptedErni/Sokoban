package es.upm.pproject.sokoban.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	private boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public boolean getupPressed() {
		return upPressed;
	}
	
	public boolean getdownPressed() {
		return downPressed;
	}
	
	public boolean getleftPressed() {
		return leftPressed;
	}
	
	public boolean getrightPressed() {
		return rightPressed;
	}

	public void keyTyped(KeyEvent k) {
	}

	@Override
	public void keyPressed(KeyEvent k) {
		int code = k.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
			int code = k.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}
