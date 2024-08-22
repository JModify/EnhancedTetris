package me.modify.tetris.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UIHelper {

    public static void addSlidersWithChangingValue(int itemYSpacing, JPanel subPanelCenter,
                                                   JPanel subPanelEast, JPanel masterPanel, JSlider... sliders) {
        GridBagConstraints gbc = new GridBagConstraints();

        int y = 1;
        for(JSlider slider : sliders) {
            JLabel label = new JLabel("Value: " + slider.getValue());
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            gbc.gridx = 0;
            gbc.gridy = y++;
            gbc.ipady = itemYSpacing;

            subPanelCenter.add(slider, gbc);
            subPanelEast.add(label, gbc);

            slider.addChangeListener(e -> tick(slider, label));
        }
        masterPanel.add(subPanelCenter, BorderLayout.CENTER);
        masterPanel.add(subPanelEast, BorderLayout.EAST);
    }

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

    public static JLabel getLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private static void tick(JSlider slider, JLabel label) {
        if (!slider.getValueIsAdjusting()) {
            label.setText("Value:" + slider.getValue());
        }
    }

}
