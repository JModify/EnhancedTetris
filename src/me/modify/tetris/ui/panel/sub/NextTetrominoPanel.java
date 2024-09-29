package me.modify.tetris.ui.panel.sub;

import me.modify.tetris.game.state.Cell;
import me.modify.tetris.game.state.Tetromino;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class NextTetrominoPanel extends JPanel {

    private final Color previewBackground = new Color(241, 241, 241);

    private Tetromino tetromino;
    public NextTetrominoPanel(Tetromino tetromino) {
        this.tetromino = tetromino;
        initContentPane();
    }

    /**
     * Initializes the next tetromino panel.
     */
    private void initContentPane() {
        SwingUtilities.invokeLater(() -> {
            setPreferredSize((new Dimension(180, 60)));
            setMinimumSize(new Dimension(180, 60));
            setMaximumSize(new Dimension(180, 60));
            setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            setBackground(previewBackground);
        });
    }

    /**
     * Handles painting for the next tetromino panel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (tetromino == null) {
            return;
        }

        int[][] shape = tetromino.getShape();

        int offsetX = 50;
        int offsetY = 10;

        int cellSize = 20;

        // For each cell in the next tetromino shape, paint a square with it's respective colour.
        for (int j = 0; j < shape[0].length; j++) {
            for (int i = 0; i < shape.length; i++) {

                int x = j * cellSize;
                int y = i * cellSize;

                int id = shape[i][j];

                // Check if the cell is a placeholder, and if it is, paint the cell transparently (same as background).
                if (id == Cell.PLACEHOLDER) {
                    g.setColor(previewBackground);
                } else {
                    g.setColor(tetromino.getColor());
                }
                g.fillRect(offsetX + x, offsetY + y, cellSize, cellSize);
            }

        }
    }
}
