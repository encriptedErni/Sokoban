package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.GameController;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class GameMenuPanel extends JPanel {
    private GameController gameController;

    public GameMenuPanel(JPanel contentPane, GameController gameController) {
        this.gameController = gameController;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Agrega un espacio interno alrededor del panel
        setBackground(new Color(50, 50, 50)); // Establece el color de fondo del panel

        // Panel para contener los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10)); // Utiliza un GridLayout para organizar los botones
        buttonPanel.setOpaque(false);

        // Estilo de fuente y colores para los botones
        Font buttonFont = new Font("Arial", Font.BOLD, 70);
        Color buttonBackgroundColor = new Color(150, 150, 150);
        Color buttonTextColor = Color.WHITE;

        // Botón "Start"
        JButton startButton = new JButton("Start");
        startButton.setFont(buttonFont);
        startButton.setBackground(buttonBackgroundColor);
        startButton.setFocusPainted(false); // quita un recuadro azul raro que salia
        startButton.setForeground(buttonTextColor);
        startButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.next(contentPane);
        });
        buttonPanel.add(startButton);

        // Botón "Exit"
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setBackground(buttonBackgroundColor);
        exitButton.setForeground(buttonTextColor);
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        // Agrega el panel de botones al centro del GameMenuPanel
        add(buttonPanel, BorderLayout.CENTER);
    }
}
