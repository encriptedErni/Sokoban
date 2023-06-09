package es.upm.pproject.sokoban.view;

import javax.swing.*;

import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.model.GameMovementCounter;
import es.upm.pproject.sokoban.model.LevelMovementCounter;

import java.awt.*;
import java.io.File;

public class GameFrame extends JFrame {
    GamePanel boardPanel;
    GameMenuPanel menuPanel;
    GameController gameController;
    GameMovementCounter gameMovementCounter;
    LevelMovementCounter levelMovementCounter;

    public GameFrame(GameController gameController) {
        this.gameController = gameController;
        this.gameMovementCounter = new GameMovementCounter();
        this.levelMovementCounter = new LevelMovementCounter();

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new CardLayout());

        this.menuPanel = new GameMenuPanel(contentPane, gameController);
        this.boardPanel = new GamePanel(gameController.getBoard(), gameController.getRows(), gameController.getCols(),
                this, gameController, gameMovementCounter, levelMovementCounter);

        contentPane.add(menuPanel);
        contentPane.add(boardPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sokoban - " + gameController.getLevelName());
        setContentPane(contentPane);
        setJMenuBar(newMenuBar());
        setLocationRelativeTo(null);
        pack();
        setSize(100 * gameController.getCols(), 100 * gameController.getRows());
        setVisible(true);
    }

    public void showCongratulationsMessage() {
        JLabel congratulationsLabel = new JLabel("Congratulations!");
        congratulationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        congratulationsLabel.setVerticalAlignment(SwingConstants.CENTER);
        congratulationsLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(congratulationsLabel, BorderLayout.CENTER);

        boardPanel.add(messagePanel);
        boardPanel.repaint();
    }

    private JMenuBar newMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Options");
        JMenuItem startNewGame = new JMenuItem("Start New Game");
        startNewGame.addActionListener(e -> {
            this.levelMovementCounter.resetMovementCount();
            this.gameMovementCounter.resetMovementCount();
            gameController.startNewGame();
            boardPanel.repaint();
        });

        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(e -> {
            this.levelMovementCounter.resetMovementCount();
            this.gameMovementCounter.resetMovementCount();
            gameController.restartGame();
            boardPanel.repaint();
        });

        JMenuItem undoMovement = new JMenuItem("Undo");
        undoMovement.addActionListener(e -> {
            if (gameController.undoMovement(levelMovementCounter.getMovementCount()-1)) {
                this.levelMovementCounter.decrementMovementCount();
                this.gameMovementCounter.decrementMovementCount();
                boardPanel.repaint();
            }
        });

        JMenuItem nextLevel = new JMenuItem("Next Level");
        nextLevel.addActionListener(e -> {
            this.levelMovementCounter.resetMovementCount();
            if (gameController.nextLevel())
                boardPanel.repaint();
        });

        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to save your game");
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                gameController.saveGame(fileToSave, gameMovementCounter.getMovementCount());
            }
        });

        JMenuItem openSavedGame = new JMenuItem("Open Saved Game");
        saveGame.addActionListener(e -> {
            gameController.openSavedGame();
            boardPanel.repaint();
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            gameController.exitGame();
        });

        menu.add(startNewGame);
        menu.add(restart);
        menu.add(nextLevel);
        menu.add(undoMovement);
        menu.add(saveGame);
        menu.add(openSavedGame);
        menu.add(exit);

        menuBar.add(menu);
        menuBar.add(gameMovementCounter);
        menuBar.add(Box.createHorizontalStrut(10));
        menuBar.add(levelMovementCounter);

        return menuBar;
    }
}
