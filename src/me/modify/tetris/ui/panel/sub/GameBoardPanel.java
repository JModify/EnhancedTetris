package me.modify.tetris.ui.panel.sub;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.*;
import me.modify.tetris.game.config.GameConfiguration;
import me.modify.tetris.game.time.GameScheduler;

import javax.swing.*;
import java.awt.*;

public class GameBoardPanel extends JPanel {

    private final int CELL_SIZE = 20;

    public GameBoardPanel(){
        init();
    }

    private void init() {
        SwingUtilities.invokeLater(() -> {
            EnhancedTetrisApp app = EnhancedTetrisApp.getInstance();
            GameGrid gameGrid = app.getGameController().getGrid();
            GameConfiguration configuration = app.getGameController().getConfiguration();

            int rows = configuration.getFieldHeight();
            int columns = configuration.getFieldWidth();

            int widthPixel = columns * CELL_SIZE;
            int heightPixel = rows * CELL_SIZE;

            setPreferredSize(new Dimension(widthPixel, heightPixel));
            // setBorder(BorderFactory.createLineBorder(Color.BLUE));
            // setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));

            gameGrid.fill();

            // Started in game controller
            GameScheduler.getInstance().addTimer("Board_Update", new Timer(0, l -> repaint()));
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameController controller = EnhancedTetrisApp.getInstance().getGameController();
        GameConfiguration configuration = controller.getConfiguration();

        int rows = configuration.getFieldHeight();
        int columns = configuration.getFieldWidth();

        int widthPixel = columns * CELL_SIZE;
        int heightPixel = rows * CELL_SIZE;

        if (controller.isPaused()) {
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.setColor(Color.RED);
            g.drawString("Game Paused", widthPixel / 12, heightPixel / 2);

            g.setFont(new Font("Arial", Font.PLAIN, 12));
            g.setColor(new Color(255, 101, 101, 255));
            g.drawString("Press p to unpause.", widthPixel / 4, (heightPixel / 2) + 14);
            return;
        }

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {

                int x = i * CELL_SIZE;
                int y = j * CELL_SIZE;

                Cell cell = controller.getGrid().getCell(j, i);

                if (cell.getData() == 0 || cell.getData() == 80 || cell.getData() == -80) {
                    g.setColor(Cell.EMPTY_CELL);
                } else {
                    g.setColor(Tetromino.getByID(cell.getData()).getColor());
                }

                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);

//                // Add cell to grid
//                EnhancedTetrisApp.getInstance().getGameController().getGrid().addCell(cell);
            }
        }


    }
}
