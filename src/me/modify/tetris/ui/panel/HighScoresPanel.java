package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.score.HighScores;
import me.modify.tetris.game.score.HighScoresFile;
import me.modify.tetris.game.score.Score;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.UIHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScoresPanel extends JPanel {

    private JPanel centerPanel;

    private JPanel rankingsPanel;
    private JPanel namesPanel;
    private JPanel scoresPanel;
    private JPanel configPanel;

    public HighScoresPanel() {
        initContentPane();
    }

    /**
     * Initializes the high scores panel.
     */
    private void initContentPane() {
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout());

            add(getTitlePanel(), BorderLayout.NORTH);

            add(Box.createHorizontalStrut(20), BorderLayout.WEST);
            add(Box.createHorizontalStrut(20), BorderLayout.EAST);

            rankingsPanel = getRanking();
            namesPanel = getNames();
            scoresPanel = getScores();
            configPanel = getConfig();

            centerPanel = new JPanel();
            centerPanel.add(rankingsPanel);
            centerPanel.add(namesPanel);
            centerPanel.add(scoresPanel);
            centerPanel.add(configPanel);

            add(centerPanel, BorderLayout.CENTER);

            add(UIHelper.getBottomPanel(new Dimension(700, 50),
                    l -> MenuFacade.openPanel(MenuType.MAIN_MENU)), BorderLayout.SOUTH);
        });
    }

    public JPanel getTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(700, 50));
        titlePanel.add(Box.createHorizontalStrut( 240));
        titlePanel.add(UIHelper.getLabel("High Scores", new Font("Arial", Font.BOLD, 34)));
        titlePanel.add(Box.createHorizontalStrut(140));

        ActionListener actionListener = e -> {
            int response = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to clear the high scores?",
                    "High Score Clear", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                HighScores highScores = EnhancedTetrisApp.getInstance().getHighScores();
                EnhancedTetrisApp.getInstance().getHighScores().clearHighScores();
                HighScoresFile.save(highScores.getScores());
                updateHighScores();
            }
        };

        titlePanel.add(UIHelper.getButton("Clear", 20, new Dimension(50, 40),
                actionListener, Component.LEFT_ALIGNMENT));
        return titlePanel;
    }

    public JPanel getRanking() {
        rankingsPanel = new JPanel();
        rankingsPanel.setLayout(new BoxLayout(rankingsPanel, BoxLayout.PAGE_AXIS));
        rankingsPanel.setPreferredSize(new Dimension(130, 400));

        rankingsPanel.add(UIHelper.getLabel("Rank",
                new Font("Arial", Font.BOLD, 22), CENTER_ALIGNMENT));

        rankingsPanel.add(Box.createVerticalStrut(10));
        for (int i = 1; i <= 10; i++) {
            rankingsPanel.add(UIHelper.getLabel(String.valueOf(i), new Font("Arial", Font.PLAIN, 16), CENTER_ALIGNMENT));
            rankingsPanel.add(Box.createVerticalStrut(10));
        }

        return rankingsPanel;
    }

    public JPanel getNames() {
        namesPanel = new JPanel();
        namesPanel.setLayout(new BoxLayout(namesPanel, BoxLayout.PAGE_AXIS));
        namesPanel.setPreferredSize(new Dimension(130, 400));

        namesPanel.add(UIHelper.getLabel("Names",
                new Font("Arial", Font.BOLD, 22), CENTER_ALIGNMENT));

        HighScores highScores = EnhancedTetrisApp.getInstance().getHighScores();

        namesPanel.add(Box.createVerticalStrut(10));
        for (Score score : highScores.getTopScores()) {
            namesPanel.add(UIHelper.getLabel(score.getName(), new Font("Arial", Font.PLAIN, 16), CENTER_ALIGNMENT));
            namesPanel.add(Box.createVerticalStrut(10));
        }

        return namesPanel;
    }

    public JPanel getScores() {
        scoresPanel = new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.PAGE_AXIS));
        scoresPanel.setPreferredSize(new Dimension(130, 400));

        scoresPanel.add(UIHelper.getLabel("Score",
                new Font("Arial", Font.BOLD, 22),
                CENTER_ALIGNMENT));

        HighScores highScores = EnhancedTetrisApp.getInstance().getHighScores();

        scoresPanel.add(Box.createVerticalStrut(10));

        for (Score score : highScores.getTopScores()) {
            scoresPanel.add(UIHelper.getLabel(String.valueOf(score.getScore()),
                    new Font("Arial", Font.PLAIN, 16), CENTER_ALIGNMENT));
            scoresPanel.add(Box.createVerticalStrut(10));
        }

        return scoresPanel;
    }

    public JPanel getConfig() {
        configPanel = new JPanel();
        configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.PAGE_AXIS));
        configPanel.setPreferredSize(new Dimension(180, 400));

        configPanel.add(UIHelper.getLabel("Config",
                new Font("Arial", Font.BOLD, 22),
                CENTER_ALIGNMENT));

        HighScores highScores = EnhancedTetrisApp.getInstance().getHighScores();

        configPanel.add(Box.createVerticalStrut(10));

        for (Score score : highScores.getTopScores()) {
            configPanel.add(UIHelper.getLabel(score.getConfig(),
                    new Font("Arial", Font.PLAIN, 16), CENTER_ALIGNMENT));
            configPanel.add(Box.createVerticalStrut(10));
        }

        return configPanel;
    }

    /**
     * Updates the high scores dynamically by refreshing the panel.
     */
    public void updateHighScores() {
        SwingUtilities.invokeLater(() -> {
            remove(centerPanel);

            centerPanel = new JPanel();
            centerPanel.add(getRanking());
            centerPanel.add(getNames());
            centerPanel.add(getScores());
            centerPanel.add(getConfig());

            add(centerPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        });
    }
}
