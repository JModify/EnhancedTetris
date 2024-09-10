package me.modify.tetris.ui.helper;

import me.modify.tetris.ui.frames.MainFrame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;

/**
 * Helper class for some of the UI in the game.
 */
public class UIHelper {

    public static JPanel getBottomPanel(Dimension dimension, ActionListener buttonActionListener) {

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.setPreferredSize(dimension);
        //bottomPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        JButton backButton = new JButton("Back");
        backButton.setUI(new BasicButtonUI());
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(buttonActionListener);
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

    public static JButton getButton(String text, int textSize, Dimension size, ActionListener actionListener, float alignment) {
        JButton button = new JButton(text);
        button.setUI(new BasicButtonUI());
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.setFont(new Font("Arial", Font.PLAIN, textSize));
        button.addActionListener(actionListener);
        button.setAlignmentX(alignment);
        return button;
    }

}
