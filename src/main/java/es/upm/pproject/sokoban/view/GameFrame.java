package es.upm.pproject.sokoban.view;

import javax.swing.*;

import es.upm.pproject.sokoban.interfaces.Controller;
import es.upm.pproject.sokoban.interfaces.Frame;
import es.upm.pproject.sokoban.model.GameMovementCounter;
import es.upm.pproject.sokoban.model.LevelMovementCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class GameFrame extends JFrame implements Frame {
    private static final Logger logger = LoggerFactory.getLogger(GameFrame.class);
    private GamePanel boardPanel;
    private transient Controller gameController;
    private GameMovementCounter gameMovementCounter;
    private LevelMovementCounter levelMovementCounter;

    public void initialize(Controller gameController) {
        setController(gameController);
        this.gameMovementCounter = new GameMovementCounter();
        this.levelMovementCounter = new LevelMovementCounter();

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new CardLayout());

        GameMenuPanel menuPanel = new GameMenuPanel(contentPane);
        this.boardPanel = new GamePanel(gameController.getBoard(), gameController.getRows(), gameController.getCols(),
                this, gameController, gameMovementCounter, levelMovementCounter);

        contentPane.add(menuPanel);
        contentPane.add(boardPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sokoban - " + gameController.getLevelName());
        setContentPane(contentPane);
        setJMenuBar(newMenuBar());
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(50 * gameController.getCols(), 50 * gameController.getRows()));
        pack();
        setVisible(true);
    }

    private JMenuBar newMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Options");
        JMenuItem startNewGame = new JMenuItem("Start New Game");
        startNewGame.addActionListener(e -> {
            this.levelMovementCounter.resetMovementCount();
            this.gameMovementCounter.resetMovementCount();
            gameController.startNewGame();
            boardPanel.startNewGame();
            boardPanel.repaint();
        });

        JMenuItem restart = new JMenuItem("Restart level");
        restart.addActionListener(e -> {
            if (gameController.restartLevel(boardPanel.getFinished())) {
                this.levelMovementCounter.resetMovementCount();
                this.gameMovementCounter.resetMovementCount();
                boardPanel.repaint();
            }
        });

        JMenuItem undoMovement = new JMenuItem("Undo");
        undoMovement.addActionListener(e -> {
            if (gameController.undoMovement(levelMovementCounter.getMovementCount()-1)) {
                this.levelMovementCounter.decrementMovementCount();
                this.gameMovementCounter.decrementMovementCount();
                boardPanel.repaint();
            }
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
        openSavedGame.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to load your game");
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = fileChooser.getSelectedFile();
                try {
                    int gamePunctuation = gameController.openSavedGame(fileToLoad);
                    if (gamePunctuation == -1) {
                        logger.error("Error loading saved game");
                        return;
                    }

                    this.gameController.parse(gameController.getActualLevel());
                    this.gameMovementCounter.setMovementCount(gamePunctuation);
                    this.gameController.doMovements(0);
                    this.levelMovementCounter.setMovementCount(gameController.getMovements().size());

                } catch (FileNotFoundException ex) {
                    logger.error("Failed not found: {}", ex.getMessage());
                }
            }
            boardPanel.repaint();
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> gameController.exitGame());

        menu.add(startNewGame);
        menu.add(restart);
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

    public void setController(Controller gameController) {
        this.gameController = gameController;
    }
}