package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.GameController;


public class Main {
	
	public static void main(String[] args) {
		
		try{
            GameController game = new GameController();
            game.parse("level1.txt");
            GameView.start(game);
        }catch (Exception e){
            System.err.println("Something went wrong! " + e);
        }
    }
		
//		JFrame window = new JFrame();
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		//cerrar con la x
//		window.setResizable(false); 		//Tamano no ajustable 
//		window.setTitle("Sokoban");
//		
//		GamePanel gamePanel1 = new GamePanel();
//		window.add(gamePanel1);
//		
//		window.pack();
//		
//		window.setLocationRelativeTo(null);		//aparece en el centro de la pantalla
//		window.setVisible(true);
//		
//		gamePanel1.startGameThread();
	}
