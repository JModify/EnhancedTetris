package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.Cell;
import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.listeners.ExitGameActionListener;
import me.modify.tetris.ui.UIHelper;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends TetrisPanel {

    private JPanel cardPanel;
    private JPanel boardPanel;
    private JPanel pausePanel;

    public void init() {
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout());

            setupGameBoard();
            setupPauseScreen();

            cardPanel = new JPanel();
            cardPanel.setLayout(new CardLayout());
            cardPanel.add(boardPanel, "Board_Panel");
            cardPanel.add(pausePanel, "Pause_Panel");

            Color veryLightGray = new Color(227, 227, 227, 255);
            JPanel topPanel = new JPanel(null);
            topPanel.setPreferredSize(new Dimension(700, 50));
            topPanel.setBackground(veryLightGray);

            JPanel leftPanel = new JPanel(null);
            leftPanel.setPreferredSize(new Dimension(225, 400));
            leftPanel.setBackground(veryLightGray);

            JPanel emptyRight = new JPanel(null);
            emptyRight.setPreferredSize(new Dimension(225, 400));
            emptyRight.setBackground(veryLightGray);

            JPanel bottomPanel = UIHelper.getBottomPanel(new Dimension(700, 50),
                    new ExitGameActionListener());
            bottomPanel.setBackground(veryLightGray);


            add(cardPanel, BorderLayout.CENTER);

            add(topPanel, BorderLayout.NORTH);
            add(leftPanel, BorderLayout.WEST);
            add(emptyRight, BorderLayout.EAST);
            add(bottomPanel, BorderLayout.SOUTH);

            updateFrame();
            EnhancedTetrisApp.getInstance().getGameController().startGame();
        });
    }

    private void setupGameBoard() {
        GameConfiguration configuration = getConfiguration();

        int rows = configuration.FIELD_HEIGHT_DEFAULT;
        int columns = configuration.FIELD_WIDTH_DEFAULT;

        boardPanel = new JPanel(new GridLayout(rows,
                columns, 0, 0));
        boardPanel.setBounds(0, 0, 250, 400);

        for (int i = 0; i < (rows * columns); i++) {
            JPanel jCell = new JPanel(null);
            jCell.setBackground(Cell.EMPTY_CELL);
            boardPanel.add(jCell);

            int x = i % columns; // column
            int y = i / columns; // row


            Cell cell = new Cell(x, y, 0, jCell);
            EnhancedTetrisApp.getInstance().getGameController().getGrid().addCell(cell);
        }
    }

    private void setupPauseScreen() {
        pausePanel = new JPanel(null);
        pausePanel.setOpaque(false);
        pausePanel.setBounds(0, 0, 250, 400);
        pausePanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

        JLabel pauseLabel = new JLabel("PAUSED");
        pauseLabel.setFont(new Font("Arial", Font.BOLD, 20));
        pauseLabel.setForeground(Color.RED);
        pauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pauseLabel.setBounds(70, 100, 100, 50);

        JLabel unpauseLabel = new JLabel("Press p to unpause");
        unpauseLabel.setFont(new Font("Arial", Font.BOLD, 12));
        unpauseLabel.setForeground(new Color(255, 101, 101, 255));
        unpauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        unpauseLabel.setBounds(20, 120, 200, 50);

        pausePanel.add(pauseLabel);
        pausePanel.add(unpauseLabel);
    }

    public void showPauseMessage() {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "Pause_Panel");
    }

    public void hidePauseMessage() {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "Board_Panel");
    }
}
