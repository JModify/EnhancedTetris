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
        init();
    }

    public void init() {
        setLayout(new BorderLayout());

        add(UIHelper.getTitlePanel("High Scores",
                new Font("Arial", Font.BOLD, 34),
                new Dimension(700, 50)), BorderLayout.NORTH);

        add(getScores(), BorderLayout.EAST);
        add(getNames(), BorderLayout.WEST);
        add(Box.createHorizontalStrut(20), BorderLayout.CENTER);
        add(UIHelper.getBottomPanel(new Dimension(700, 50),
                l -> MenuFacade.openPanel(MenuType.MAIN_MENU)), BorderLayout.SOUTH);

    }

    public JPanel getNames() {
        JPanel names = new JPanel();
        names.setLayout(new BoxLayout(names, BoxLayout.PAGE_AXIS));

        names.setPreferredSize(new Dimension(340, 400));
//        names.setMinimumSize(new Dimension(300, 400));
//        names.setMaximumSize(new Dimension(300, 400));

        names.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        names.add(UIHelper.getLabel("Names",
                new Font("Arial", Font.BOLD, 24), CENTER_ALIGNMENT));

        HighScores highScores = EnhancedTetrisApp.getInstance().getHighScores();

        for (Score score : highScores.getTopScores()) {
            names.add(UIHelper.getLabel(score.getName(), new Font("Arial", Font.PLAIN, 12), CENTER_ALIGNMENT));
            names.add(Box.createVerticalStrut(5));
        }

        return names;
    }

    public JPanel getScores() {
        JPanel scores = new JPanel();
        scores.setLayout(new BoxLayout(scores, BoxLayout.PAGE_AXIS));
        scores.setPreferredSize(new Dimension(340, 400));
//        scores.setMinimumSize(new Dimension(300, 400));
//        scores.setMaximumSize(new Dimension(300, 400));

        scores.setBorder(BorderFactory.createLineBorder(Color.RED));

        scores.add(UIHelper.getLabel("Score",
                new Font("Arial", Font.BOLD, 24),
                CENTER_ALIGNMENT));

        HighScores highScores = EnhancedTetrisApp.getInstance().getHighScores();

        for (Score score : highScores.getTopScores()) {
            scores.add(UIHelper.getLabel(String.valueOf(score.getScore()),
                    new Font("Arial", Font.PLAIN, 12), CENTER_ALIGNMENT));
            scores.add(Box.createVerticalStrut(5));
        }

        return scores;
    }
}
