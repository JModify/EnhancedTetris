package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.scores.HighScores;
import me.modify.tetris.scores.Score;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.UIHelper;

import javax.swing.*;
import java.awt.*;

public class HighScoresPanel extends JPanel {

    public HighScoresPanel() {
        initContentPane();
    }

    public void initContentPane() {
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout());

            add(UIHelper.getTitlePanel("High Scores",
                    new Font("Arial", Font.BOLD, 34),
                    new Dimension(700, 50)), BorderLayout.NORTH);

            add(getScores(), BorderLayout.EAST);
            add(getNames(), BorderLayout.WEST);

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

        for (Score score : highScores.getTopScores()) {
            namesPanel.add(UIHelper.getLabel(score.getName(), new Font("Arial", Font.PLAIN, 12), CENTER_ALIGNMENT));
            namesPanel.add(Box.createVerticalStrut(5));
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

        for (Score score : highScores.getTopScores()) {
            scoresPanel.add(UIHelper.getLabel(String.valueOf(score.getScore()),
                    new Font("Arial", Font.PLAIN, 12), CENTER_ALIGNMENT));
            scoresPanel.add(Box.createVerticalStrut(5));
        }

        return scoresPanel;
    }

    public void updateContentPane() {
        removeAll();
        initContentPane();
    }
}
