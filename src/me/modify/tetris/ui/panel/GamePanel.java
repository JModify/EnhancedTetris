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

    private JPanel boardPanel;
    private JPanel pausePanel;

    public GamePanel(MainFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    public void paint() {
        GameController gameController = getMainFrame().getTetrisApp().getGameController();

        SwingUtilities.invokeLater(() -> {
            setPanel(new JPanel(new BorderLayout()));

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
                gameController.getGrid().addCell(cell);
            }

            pausePanel = new JPanel(null);
            pausePanel.setOpaque(false);
            pausePanel.setVisible(false);
            pausePanel.setBounds(0, 0, 250, 400);
            pausePanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

            addPauseLabels();

            JPanel overlayPanel = new JPanel();
            overlayPanel.setLayout(new OverlayLayout(overlayPanel));
            overlayPanel.add(boardPanel);
            overlayPanel.add(pausePanel);

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

            JPanel bottomPanel = getBottomPanel(new Dimension(700, 50));
            bottomPanel.setBackground(veryLightGray);


            panel.add(overlayPanel, BorderLayout.CENTER);
            panel.add(topPanel, BorderLayout.NORTH);
            panel.add(leftPanel, BorderLayout.WEST);
            panel.add(emptyRight, BorderLayout.EAST);
            panel.add(bottomPanel, BorderLayout.SOUTH);

            update(panel);
            gameController.startGame();
        });
    }

    private void addPauseLabels() {
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

    private JPanel getBottomPanel(Dimension dimension) {

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

    public void showPauseMessage() {
        pausePanel.setVisible(true);
        boardPanel.setVisible(false);
    }

    public void hidePauseMessage() {
        pausePanel.setVisible(false);
        boardPanel.setVisible(true);
    }
}
