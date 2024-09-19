package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.Cell;
import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.listeners.ExitGameActionListener;
import me.modify.tetris.ui.UIHelper;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        setLayout(new BorderLayout());
        addGameBoard();
        addSubPanels();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.setColor(Color.RED);
        g2d.drawString("Paused", 0, 0);
        repaint();
    }

    private void addGameBoard() {
        GameConfiguration configuration = EnhancedTetrisApp.getInstance().getGameController().getConfiguration();

        int rows = configuration.FIELD_HEIGHT_DEFAULT;
        int columns = configuration.FIELD_WIDTH_DEFAULT;

        JPanel boardPanel = new JPanel(new GridLayout(rows,
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

        add(boardPanel);
    }

    private void addSubPanels() {
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


        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(emptyRight, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
