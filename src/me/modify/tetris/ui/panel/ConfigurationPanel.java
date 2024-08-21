package me.modify.tetris.ui.panel;

import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.ui.MainFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationPanel extends TPanel {

    private JPanel panel;

    public ConfigurationPanel(MainFrame main) {
        super(main);
        panel = new JPanel(null);
    }

    @Override
    public void load() {
        panel = new JPanel(new BorderLayout());

        // Title
        JLabel configurationTitle = new JLabel("Configuration");
        configurationTitle.setHorizontalAlignment(SwingConstants.CENTER);
        configurationTitle.setFont(new Font("Arial", Font.BOLD, 40));
        panel.add(configurationTitle, BorderLayout.NORTH);

        // Label Panel
        List<String> labels = new ArrayList<>();
        labels.add("Field Width (Cells):");
        labels.add("Field Height (Cells):");
        labels.add("Game Level:");
        labels.add("Music (On/Off):");
        labels.add("Sound Effect (On/Off):");
        labels.add("AI Play (On/Off):");
        labels.add("Extend Mode (On/Off):");

        configTitleSubPanel(labels, new Font("Arial", Font.BOLD, 16),
                200, 35, panel);

        GameConfiguration configuration = getConfiguration();

        // Right Panel
        JPanel eastPanel = new JPanel(null);
        eastPanel.setLayout(new GridBagLayout());
        eastPanel.setPreferredSize(new Dimension(100, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.ipady = 35;

        Font font = new Font("Arial", Font.PLAIN, 16);
        JLabel fieldWidthSelection = getLabel(
                String.valueOf(configuration.FIELD_WIDTH_DEFAULT), font);
        gbc.gridy = 1;
        eastPanel.add(fieldWidthSelection, gbc);

        JLabel fieldHeightSelection = getLabel(
                String.valueOf(configuration.FIELD_HEIGHT_DEFAULT), font);
        gbc.gridy = 2;
        eastPanel.add(fieldHeightSelection, gbc);

        JLabel gameLevelSelection = getLabel(
                String.valueOf(configuration.GAME_LEVEL_DEFAULT), font);
        gbc.gridy = 3;
        eastPanel.add(gameLevelSelection, gbc);

        JLabel musicSelection = getLabel(configuration.CHECKBOX_OFF, font);
        gbc.gridy = 4;
        eastPanel.add(musicSelection, gbc);

        JLabel soundEffectsSelection = getLabel(configuration.CHECKBOX_OFF, font);
        gbc.gridy = 5;
        eastPanel.add(soundEffectsSelection, gbc);

        JLabel aiPlaySelection = getLabel(configuration.CHECKBOX_OFF, font);
        gbc.gridy = 6;
        eastPanel.add(aiPlaySelection, gbc);

        JLabel extendModeSelection = getLabel(configuration.CHECKBOX_OFF, font);
        gbc.gridy = 7;
        eastPanel.add(extendModeSelection, gbc);

        panel.add(eastPanel, BorderLayout.EAST);
        // Right Panel

        // Center Panel
        JPanel centerPanel = new JPanel(null);
        JSlider fieldWidthSlider = getNumericalSlider(configuration.FIELD_WIDTH_MIN,
                configuration.FIELD_WIDTH_MAX,
                configuration.FIELD_WIDTH_DEFAULT);
        fieldWidthSlider.setBounds(20, 13, 300, 40);
        centerPanel.add(fieldWidthSlider);
        fieldWidthSlider.addChangeListener(e -> update(fieldWidthSlider, fieldWidthSelection));

        JSlider fieldHeightSlider = getNumericalSlider(configuration.FIELD_HEIGHT_MIN,
                configuration.FIELD_HEIGHT_MAX,
                configuration.FIELD_HEIGHT_DEFAULT);
        fieldHeightSlider.setBounds(20, 63, 300, 40);
        centerPanel.add(fieldHeightSlider);
        fieldHeightSlider.addChangeListener(e -> update(fieldHeightSlider, fieldHeightSelection));

        JSlider gameLevelSlider = getNumericalSlider(configuration.GAME_LEVEL_MIN,
                configuration.GAME_LEVEL_MAX,
                configuration.GAME_LEVEL_DEFAULT);
        gameLevelSlider.setBounds(20, 113, 300, 40);
        centerPanel.add(gameLevelSlider);
        gameLevelSlider.addChangeListener(e -> update(gameLevelSlider, gameLevelSelection));

        JCheckBox musicCheckBox = new JCheckBox();
        musicCheckBox.setBounds(20,160, 300, 40);
        centerPanel.add(musicCheckBox);
        musicCheckBox.addChangeListener(e -> update(musicCheckBox, musicSelection));

        JCheckBox soundEffectsCheckBox = new JCheckBox();
        soundEffectsCheckBox.setBounds(20,215, 300, 40);
        centerPanel.add(soundEffectsCheckBox);
        soundEffectsCheckBox.addChangeListener(e -> update(soundEffectsCheckBox, soundEffectsSelection));

        JCheckBox aiCheckBox = new JCheckBox();
        aiCheckBox.setBounds(20,270, 300, 40);
        centerPanel.add(aiCheckBox);
        aiCheckBox.addChangeListener(e -> update(aiCheckBox, aiPlaySelection));

        JCheckBox extendModeCheckBox = new JCheckBox();
        extendModeCheckBox.setBounds(20,325, 300, 40);
        centerPanel.add(extendModeCheckBox);
        extendModeCheckBox.addChangeListener(e -> update(extendModeCheckBox, extendModeSelection));

        panel.add(centerPanel, BorderLayout.CENTER);
        // Center Panel

        // Buttom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));

        JButton backButton = new JButton("Back");
        backButton.setUI(new BasicButtonUI());
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMainFrame().openMainMenu();
            }
        });

        bottomPanel.add(Box.createVerticalStrut(10));

        JLabel authorLabel = new JLabel("Author: Joshua Lavagna-Slater");
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(authorLabel);

        panel.add(bottomPanel, BorderLayout.SOUTH);
        // Bottom Panel

        update(panel);
    }

    private JSlider getNumericalSlider(int min, int max, int init) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
        slider.setUI(new BasicSliderUI());

        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setFocusable(false);
        slider.setForeground(Color.DARK_GRAY);


        return slider;
    }

    private void update(JSlider slider, JLabel label) {
        if (!slider.getValueIsAdjusting()) {
            label.setText(String.valueOf(slider.getValue()));
        }
    }

    private void update(JCheckBox checkBox, JLabel label) {
        boolean selected = checkBox.isSelected();

        if (selected) {
            label.setText(getConfiguration().CHECKBOX_ON);
        } else {
            label.setText(getConfiguration().CHECKBOX_OFF);
        }
    }

    private void configTitleSubPanel(List<String> entries, Font font,
                                           int panelWidth, int itemYSpacing,
                                           JPanel masterPanel) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        subPanel.setPreferredSize(new Dimension(panelWidth, 0));

        GridBagConstraints gbc = new GridBagConstraints();

        int y = 1;
        for(String text : entries) {
            JLabel label = new JLabel(text);
            label.setFont(font);
            //label.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.gridx = 0;
            gbc.gridy = y++;
            gbc.ipady = itemYSpacing;

            subPanel.add(label, gbc);
        }

        masterPanel.add(subPanel, BorderLayout.WEST);
    }

    public JLabel getLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    public void saveConfiguration() {

    }
}
