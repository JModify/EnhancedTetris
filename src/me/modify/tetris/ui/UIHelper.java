package me.modify.tetris.ui;

import me.modify.tetris.ui.frames.MainFrame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

/**
 * Helper class for some of the UI in the game.
 */
public class UIHelper {

    public static JPanel getBottomPanel(Dimension dimension, MainFrame mainFrame) {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.setPreferredSize(dimension);
        //bottomPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        JButton backButton = new JButton("Back");
        backButton.setUI(new BasicButtonUI());
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> mainFrame.openMainMenu());
        bottomPanel.add(backButton);
        JLabel authorLabel = new JLabel("Author: Joshua Lavagna-Slater");
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(authorLabel);
        return bottomPanel;
    }

    /**
     * Retrieves the JLabel using the provided information.
     * @param text text of label
     * @param font font of label
     * @return the respective JLabel
     */
    public static JLabel getLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

}
