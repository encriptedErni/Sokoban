package es.upm.pproject.sokoban.view;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;

import es.upm.pproject.sokoban.controller.GameController;

public class GameView implements Runnable{
	JFrame frame;
	GameController game;
	boolean fullScreen;
	
	public GameView(GameController game) {
		this.game=game;
	}
	
	public static void start(GameController game) {
		SwingUtilities.invokeLater((Runnable) new GameView(game));
	}
	
	public void run() {
		frame = new JFrame("Sokoban");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Pa cerrarlo bien 
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null); 	// centrau
		frame.setVisible(true); 	//	 Pa que se vea
	}
	
	public void toggleFullScreen(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        if(fullScreen){
            device.setFullScreenWindow(null);
            fullScreen = false;
        }else{
            device.setFullScreenWindow(frame);
            fullScreen = true;
        }
	}

}
