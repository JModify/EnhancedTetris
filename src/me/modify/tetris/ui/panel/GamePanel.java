package me.modify.tetris.ui.panel;

import me.modify.tetris.game.Cell;
import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.frames.GameQuitConfirmation;
import me.modify.tetris.ui.frames.MainFrame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.Arrays;

public class GamePanel  extends TPanel {

    private final Color BOARD_BORDER_COLOR = Color.BLACK;

    private JPanel leftPanel;

    public GamePanel(MainFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    public void paint() {
        GameController gameController = getMainFrame().getTetrisApp().getGameController();

        SwingUtilities.invokeLater(() -> {
            setPanel(new JPanel(new BorderLayout()));
            //panel.setBounds(0, 0, 700, 500);

            GameConfiguration configuration = getConfiguration();

            //int rows = configuration.getFieldHeight();
            //int columns = configuration.getFieldWidth();
            int rows = configuration.FIELD_HEIGHT_DEFAULT;
            int columns = configuration.FIELD_WIDTH_DEFAULT;

            JPanel boardPanel = new JPanel(new GridLayout(rows,
                    columns, 0, 0));

            for (int i = 0; i < (rows * columns); i++) {
                JPanel jCell = new JPanel(null);
                //jCell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                jCell.setBackground(Cell.EMPTY_CELL);
                boardPanel.add(jCell);

                int x = i % columns; // column
                int y = i / columns; // row

//            System.out.println("X: " + x + " | Y: " + y);

                Cell cell = new Cell(x, y, 0, jCell);
                gameController.getGrid().addCell(cell);
            }

            //gameController.getGrid().printGrid();

            Color veryLightGray = new Color(227, 227, 227, 255);

            JPanel topPanel = new JPanel(null);
            topPanel.setPreferredSize(new Dimension(700, 50));
            topPanel.setBackground(veryLightGray);
            //emptyTop.setBorder(BorderFactory.createLineBorder(Color.RED));

            leftPanel = new JPanel(null);
            leftPanel.setPreferredSize(new Dimension(225, 400));
            leftPanel.setBackground(veryLightGray);
            //emptyLeft.setBorder(BorderFactory.createLineBorder(Color.RED));

            JPanel emptyRight = new JPanel(null);
            emptyRight.setPreferredSize(new Dimension(225, 400));
            emptyRight.setBackground(veryLightGray);
            //emptyRight.setBorder(BorderFactory.createLineBorder(Color.RED));

            JPanel bottomPanel = getBottomPanel(new Dimension(700, 50));
            bottomPanel.setBackground(veryLightGray);

            panel.add(boardPanel, BorderLayout.CENTER);

            panel.add(topPanel, BorderLayout.NORTH);
            panel.add(leftPanel, BorderLayout.WEST);
            panel.add(emptyRight, BorderLayout.EAST);
            panel.add(bottomPanel, BorderLayout.SOUTH);

            update(panel);
            gameController.startGame();
        });
    }

//    public void showPauseMessage() {
//        Arrays.stream(leftPanel.getComponents()).anyMatch(c -> c.get)
//    }
//
//    public void hidePauseMessage() {
//
//    }

    public JPanel getBottomPanel(Dimension dimension) {

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.setPreferredSize(dimension);

        JButton backButton = new JButton("Back");
        backButton.setUI(new BasicButtonUI());
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.addActionListener(e -> {
            GameController gameController = getMainFrame().getTetrisApp().getGameController();
            if (gameController.isGameOver()) {
                getMainFrame().openMainMenu();
                return;
            }

            if (!gameController.isPaused()) {
                gameController.setTempPause(true);
                gameController.pauseGame();
            }

            GameQuitConfirmation gameQuitConfirmation = new GameQuitConfirmation(getMainFrame());
            gameQuitConfirmation.open();
        });


        bottomPanel.add(backButton);

        JLabel authorLabel = new JLabel("Author: Joshua Lavagna-Slater");
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(authorLabel);
        return bottomPanel;
    }
}
