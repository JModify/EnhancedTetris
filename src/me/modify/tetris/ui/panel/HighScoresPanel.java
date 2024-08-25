package me.modify.tetris.ui.panel;

import me.modify.tetris.ui.frames.MainFrame;
import me.modify.tetris.ui.UIHelper;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class HighScoresPanel extends TPanel {

    public HighScoresPanel(MainFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    public void paint() {
        SwingUtilities.invokeLater(() -> {
            setPanel(new JPanel(new BorderLayout()));

            JPanel titlePanel = new JPanel();
            titlePanel.setPreferredSize(new Dimension(700, 50));
            JLabel titleLabel = new JLabel("High Scores");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
            titlePanel.add(titleLabel);

            JPanel namePanel = new JPanel(new GridBagLayout());
            //namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            namePanel.setPreferredSize(new Dimension(320, 400));
            addDummyNamePanel(namePanel);

            JPanel scorePanel = new JPanel(new GridBagLayout());
            //scorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            namePanel.setPreferredSize(new Dimension(320, 400));
            addDummyScoresPanel(scorePanel);

            JPanel bottomPanel = UIHelper.getBottomPanel(new Dimension(700, 50), getMainFrame());

            JPanel centerPanel = new JPanel();
            centerPanel.setPreferredSize(new Dimension(50, 400));
            centerPanel.add(new JLabel("Text"));

            panel.add(titlePanel, BorderLayout.NORTH);
            panel.add(scorePanel, BorderLayout.EAST);
            panel.add(centerPanel, BorderLayout.CENTER);
            panel.add(namePanel, BorderLayout.WEST);
            panel.add(bottomPanel, BorderLayout.SOUTH);


            update(panel);
        });
    }

    private void addDummyNamePanel(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 15;

        JLabel nameTitle = new JLabel("Name");
        nameTitle.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(nameTitle, gbc);
        gbc.gridy++;

        for (int i = 0; i < 10; i++) {
            JLabel name = new JLabel("Josh");
            panel.add(name, gbc);
            gbc.gridy++;
        }
    }

    private void addDummyScoresPanel(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 15;

        JLabel scoresTitle = new JLabel("Scores");
        scoresTitle.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(scoresTitle, gbc);
        gbc.gridy++;

        for (int i = 0; i < 10; i++) {
            JLabel name = new JLabel(String.valueOf(new Random().nextInt(100000 - 1000 + 1)));
            panel.add(name, gbc);
            gbc.gridy++;
        }

        // Trick BorderLayout to not make center quadrant prioritise space.
        JPanel rightSpacer = new JPanel();
        rightSpacer.setPreferredSize(new Dimension(320, 400));
        panel.add(rightSpacer);
    }
}
