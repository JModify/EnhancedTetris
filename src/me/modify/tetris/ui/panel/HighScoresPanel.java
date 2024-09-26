package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.scores.HighScores;
import me.modify.tetris.scores.Score;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.UIHelper;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class HighScoresPanel extends JPanel {

    private JPanel scoresPanel;
    private JPanel namesPanel;

    public HighScoresPanel() {
        initContentPane();
    }

    public void initContentPane() {
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout());

            add(UIHelper.getTitlePanel("High Scores",
                    new Font("Arial", Font.BOLD, 34),
                    new Dimension(700, 50)), BorderLayout.NORTH);

            scoresPanel = getScores();
            namesPanel = getNames();

            add(scoresPanel, BorderLayout.EAST);
            add(namesPanel, BorderLayout.WEST);

            add(Box.createHorizontalStrut(20), BorderLayout.CENTER);
            add(UIHelper.getBottomPanel(new Dimension(700, 50),
                    l -> MenuFacade.openPanel(MenuType.MAIN_MENU)), BorderLayout.SOUTH);
        });
    }

    public JPanel getNames() {
        JPanel namesPanel = new JPanel();
        namesPanel.setLayout(new BoxLayout(namesPanel, BoxLayout.PAGE_AXIS));

        namesPanel.setPreferredSize(new Dimension(340, 400));
//        names.setMinimumSize(new Dimension(300, 400));
//        names.setMaximumSize(new Dimension(300, 400));

        //namesPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        namesPanel.add(UIHelper.getLabel("Names",
                new Font("Arial", Font.BOLD, 24), CENTER_ALIGNMENT));

        HighScores highScores = EnhancedTetrisApp.getInstance().getHighScores();

        namesPanel.add(Box.createVerticalStrut(10));
        for (Score score : highScores.getTopScores()) {
            namesPanel.add(UIHelper.getLabel(score.getName(), new Font("Arial", Font.PLAIN, 18), CENTER_ALIGNMENT));
            namesPanel.add(Box.createVerticalStrut(10));
        }

        return namesPanel;
    }

    public JPanel getScores() {
        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.PAGE_AXIS));
        scoresPanel.setPreferredSize(new Dimension(340, 400));

        //scoresPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

        scoresPanel.add(UIHelper.getLabel("Score",
                new Font("Arial", Font.BOLD, 24),
                CENTER_ALIGNMENT));

        HighScores highScores = EnhancedTetrisApp.getInstance().getHighScores();

        scoresPanel.add(Box.createVerticalStrut(10));

        for (Score score : highScores.getTopScores()) {
            scoresPanel.add(UIHelper.getLabel(String.valueOf(score.getScore()),
                    new Font("Arial", Font.PLAIN, 18), CENTER_ALIGNMENT));
            scoresPanel.add(Box.createVerticalStrut(10));
        }

        return scoresPanel;
    }

    /**
     * Updates the high scores dynamically by refreshing the panel.
     */
    public void updateHighScores() {
        SwingUtilities.invokeLater(() -> {
            remove(scoresPanel);
            remove(namesPanel);
            scoresPanel = getScores();
            namesPanel = getNames();

            add(scoresPanel, BorderLayout.EAST);
            add(namesPanel, BorderLayout.WEST);
            revalidate();
            repaint();
        });
    }
}
