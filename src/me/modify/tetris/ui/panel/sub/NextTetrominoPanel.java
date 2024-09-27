package me.modify.tetris.ui.panel.sub;

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

    private void initContentPane() {
        SwingUtilities.invokeLater(() -> {
            setPreferredSize((new Dimension(180, 60)));
            setMinimumSize(new Dimension(180, 60));
            setMaximumSize(new Dimension(180, 60));
            setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            setBackground(previewBackground);
        });
    }

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

        for (int j = 0; j < shape[0].length; j++) {
            for (int i = 0; i < shape.length; i++) {

                int x = j * cellSize;
                int y = i * cellSize;

                int id = shape[i][j];

                if (id == 80) {
                    g.setColor(previewBackground);
                } else {
                    g.setColor(tetromino.getColor());
                }
                g.fillRect(offsetX + x, offsetY + y, cellSize, cellSize);
            }

        }
    }

    public void setTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
    }
}
