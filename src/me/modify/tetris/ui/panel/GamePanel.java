package me.modify.tetris.ui.panel;

import me.modify.tetris.game.Cell;
import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.game.GameController;
import me.modify.tetris.game.TetrisGrid;
import me.modify.tetris.game.control.MovementListener;
import me.modify.tetris.ui.MainFrame;
import me.modify.tetris.ui.UIHelper;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel  extends TPanel {

    public GamePanel(MainFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    public void paint() {
        GameController gameController = getMainFrame().getTetrisApp().getGameController();

        SwingUtilities.invokeLater(() -> {
            setPanel(new JPanel(new BorderLayout()));

            GameConfiguration configuration = getConfiguration();

            int rows = configuration.getFieldHeight();
            int columns = configuration.getFieldWidth();

            gameController.getGrid().updateSize(columns, rows);

            JPanel boardPanel = new JPanel(new GridLayout(rows,
                    columns, 0, 0));
            boardPanel.setBorder(new LineBorder(Color.BLACK));

            for (int i = 0; i < (rows * columns); i++) {
                JPanel jCell = new JPanel(null);
                //jCell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                jCell.setBackground(Color.WHITE);
                boardPanel.add(jCell);

                int x = i % columns; // column
                int y = i / columns; // row

//            System.out.println("X: " + x + " | Y: " + y);

                Cell cell = new Cell(x, y, 0, jCell);
                gameController.getGrid().addCell(cell);
            }

            gameController.getGrid().printGrid();

            JPanel emptyTop = new JPanel(null);
            emptyTop.setPreferredSize(new Dimension(700, 50));
            //emptyTop.setBorder(BorderFactory.createLineBorder(Color.RED));

            JPanel emptyLeft = new JPanel(null);
            emptyLeft.setPreferredSize(new Dimension(225, 400));
            //emptyLeft.setBorder(BorderFactory.createLineBorder(Color.RED));

            JPanel emptyRight = new JPanel(null);
            emptyRight.setPreferredSize(new Dimension(225, 400));
            //emptyRight.setBorder(BorderFactory.createLineBorder(Color.RED));


            JPanel bottomPanel = UIHelper.getBottomPanel(new Dimension(700, 50), getMainFrame());

            panel.add(boardPanel, BorderLayout.CENTER);

            panel.add(emptyTop, BorderLayout.NORTH);
            panel.add(emptyLeft, BorderLayout.WEST);
            panel.add(emptyRight, BorderLayout.EAST);
            panel.add(bottomPanel, BorderLayout.SOUTH);

            update(panel);
            gameController.startGame();
        });
    }

    public void colourCells() {

    }
}
