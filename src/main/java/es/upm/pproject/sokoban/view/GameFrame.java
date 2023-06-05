package es.upm.pproject.sokoban.view;

import javax.swing.*;

import es.upm.pproject.sokoban.controller.GameController;

import java.awt.*;

public class GameFrame extends JFrame {
    GamePanel boardPanel;
    GameMenuPanel menuPanel;
    GameController gameController;

    public GameFrame(GameController gameController) {
        this.gameController = gameController;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sokoban - " + gameController.getLevelName());

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new CardLayout());
        this.menuPanel = new GameMenuPanel(contentPane, gameController);
        this.boardPanel = new GamePanel(gameController.getBoard(), gameController.getRows(), gameController.getCols(), this, gameController);

        contentPane.add(menuPanel);
        contentPane.add(boardPanel);

        setContentPane(contentPane);

        setJMenuBar(newMenuBar());
        setLocationRelativeTo(null);
        pack();
        setSize(50 * gameController.getCols(), 50 * gameController.getRows());
        setVisible(true);
    }

    private JMenuBar newMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(e -> {
            gameController.restartGame();
            boardPanel.repaint();
        });
        menuBar.add(restart);
        return menuBar;
    }
}
