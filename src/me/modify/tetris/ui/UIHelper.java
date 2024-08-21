package me.modify.tetris.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

    public static void addCheckbox(int total, int itemYSpacing, JPanel subPanel, JPanel masterPanel) {

    }

    private static void tick(JSlider slider, JLabel label) {
        if (!slider.getValueIsAdjusting()) {
            label.setText("Value:" + slider.getValue());
        }
    }

}
