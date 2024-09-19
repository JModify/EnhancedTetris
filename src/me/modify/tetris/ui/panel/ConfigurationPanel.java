package me.modify.tetris.ui.panel;

import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.UIHelper;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationPanel extends TetrisPanel {

    private JLabel fieldWidthSelection;
    private JLabel fieldHeightSelection;
    private JLabel gameLevelSelection;
    private JLabel musicSelection;
    private JLabel soundEffectsSelection;
    private JLabel aiPlaySelection;
    private JLabel extendModeSelection;

    @Override
    public void init() {
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout());

            // Draw Configuration Panel title.
            JLabel configurationTitle = new JLabel("Configuration");
            configurationTitle.setHorizontalAlignment(SwingConstants.CENTER);
            configurationTitle.setFont(new Font("Arial", Font.BOLD, 40));
            add(configurationTitle, BorderLayout.NORTH);

            // Draws labels / titles for given configuration.
            drawConfigLabels();

            // Draws selections panel which displays current selection for a given configuration.
            drawConfigSelections();

            // Draws interactive configuration. Includes sliders and checkboxes.
            drawConfigInteractives();

            // Draws back button (return to main menu) and author name below it.
            drawBackButton();

            updateFrame();
        });
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

    private void drawConfigLabels() {
        List<String> labels = new ArrayList<>();
        labels.add("Field Width (Cells):");
        labels.add("Field Height (Cells):");
        labels.add("Game Level:");
        labels.add("Music (On/Off):");
        labels.add("Sound Effect (On/Off):");
        labels.add("AI Play (On/Off):");
        labels.add("Extend Mode (On/Off):");
        titleSubPanelHelper(labels, new Font("Arial", Font.BOLD, 16),
                this);
    }

    private void drawConfigInteractives() {
        JPanel interactivesPanel = new JPanel(null);
        GameConfiguration configuration = getConfiguration();
        JSlider fieldWidthSlider = getNumericalSlider(configuration.FIELD_WIDTH_MIN,
                configuration.FIELD_WIDTH_MAX,
                configuration.getFieldWidth());
        fieldWidthSlider.setBounds(20, 13, 300, 40);
        interactivesPanel.add(fieldWidthSlider);
        fieldWidthSlider.addChangeListener(e -> update(fieldWidthSlider, fieldWidthSelection));

        JSlider fieldHeightSlider = getNumericalSlider(configuration.FIELD_HEIGHT_MIN,
                configuration.FIELD_HEIGHT_MAX,
                configuration.getFieldHeight());
        fieldHeightSlider.setBounds(20, 63, 300, 40);
        interactivesPanel.add(fieldHeightSlider);
        fieldHeightSlider.addChangeListener(e -> update(fieldHeightSlider, fieldHeightSelection));

        JSlider gameLevelSlider = getNumericalSlider(configuration.GAME_LEVEL_MIN,
                configuration.GAME_LEVEL_MAX,
                configuration.getGameLevel());
        gameLevelSlider.setBounds(20, 113, 300, 40);
        interactivesPanel.add(gameLevelSlider);
        gameLevelSlider.addChangeListener(e -> update(gameLevelSlider, gameLevelSelection));

        JCheckBox musicCheckBox = new JCheckBox();
        musicCheckBox.setSelected(configuration.isMusic());
        musicCheckBox.setBounds(20,160, 300, 40);
        interactivesPanel.add(musicCheckBox);
        musicCheckBox.addChangeListener(e -> update(musicCheckBox, musicSelection));

        JCheckBox soundEffectsCheckBox = new JCheckBox();
        soundEffectsCheckBox.setSelected(configuration.isSoundEffects());
        soundEffectsCheckBox.setBounds(20,215, 300, 40);
        interactivesPanel.add(soundEffectsCheckBox);
        soundEffectsCheckBox.addChangeListener(e -> update(soundEffectsCheckBox, soundEffectsSelection));

        JCheckBox aiCheckBox = new JCheckBox();
        aiCheckBox.setSelected(configuration.isAiPlay());
        aiCheckBox.setBounds(20,270, 300, 40);
        interactivesPanel.add(aiCheckBox);
        aiCheckBox.addChangeListener(e -> update(aiCheckBox, aiPlaySelection));

        JCheckBox extendModeCheckBox = new JCheckBox();
        extendModeCheckBox.setSelected(configuration.isExtendMode());
        extendModeCheckBox.setBounds(20,325, 300, 40);
        interactivesPanel.add(extendModeCheckBox);
        extendModeCheckBox.addChangeListener(e -> update(extendModeCheckBox, extendModeSelection));

        add(interactivesPanel, BorderLayout.CENTER);
    }

    private void drawConfigSelections() {
        JPanel selectionsPanel = new JPanel(null);
        GameConfiguration configuration = getConfiguration();
        selectionsPanel.setLayout(new GridBagLayout());
        selectionsPanel.setPreferredSize(new Dimension(100, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.ipady = 35;

        Font font = new Font("Arial", Font.PLAIN, 16);
        fieldWidthSelection = UIHelper.getLabel(
                String.valueOf(configuration.getFieldWidth()), font);
        gbc.gridy = 1;
        selectionsPanel.add(fieldWidthSelection, gbc);

        fieldHeightSelection = UIHelper.getLabel(
                String.valueOf(configuration.getFieldHeight()), font);
        gbc.gridy = 2;
        selectionsPanel.add(fieldHeightSelection, gbc);

        gameLevelSelection = UIHelper.getLabel(
                String.valueOf(configuration.getGameLevel()), font);
        gbc.gridy = 3;
        selectionsPanel.add(gameLevelSelection, gbc);

        musicSelection = UIHelper.getLabel(configuration.isMusic() ? "ON" : "OFF", font);
        gbc.gridy = 4;
        selectionsPanel.add(musicSelection, gbc);

        soundEffectsSelection = UIHelper.getLabel(configuration.isSoundEffects() ? "ON" : "OFF", font);
        gbc.gridy = 5;
        selectionsPanel.add(soundEffectsSelection, gbc);

        aiPlaySelection = UIHelper.getLabel(configuration.isAiPlay() ? "ON" : "OFF", font);
        gbc.gridy = 6;
        selectionsPanel.add(aiPlaySelection, gbc);

        extendModeSelection = UIHelper.getLabel(configuration.isExtendMode() ? "ON" : "OFF", font);
        gbc.gridy = 7;
        selectionsPanel.add(extendModeSelection, gbc);

        add(selectionsPanel, BorderLayout.EAST);
    }

    private void drawBackButton() {
        JPanel bottomPanel = UIHelper.getBottomPanel(new Dimension(700, 50), e -> {
            saveConfiguration();
            MenuFacade.openPanel(MenuType.MAIN_MENU);
        });

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void titleSubPanelHelper(List<String> entries, Font font,
                                     JPanel masterPanel) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        subPanel.setPreferredSize(new Dimension(200, 0));

        GridBagConstraints gbc = new GridBagConstraints();

        int y = 1;
        for(String text : entries) {
            JLabel label = new JLabel(text);
            label.setFont(font);
            //label.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.gridx = 0;
            gbc.gridy = y++;
            gbc.ipady = 35;

            subPanel.add(label, gbc);
        }

        masterPanel.add(subPanel, BorderLayout.WEST);
    }

    public void saveConfiguration() {
        GameConfiguration configuration = getConfiguration();

        try {
            configuration.setFieldWidth(Integer.parseInt(fieldWidthSelection.getText()));
            configuration.setFieldHeight(Integer.parseInt(fieldHeightSelection.getText()));
            configuration.setGameLevel(Integer.parseInt(gameLevelSelection.getText()));

            configuration.setMusic(musicSelection.getText().equalsIgnoreCase("ON"));
            configuration.setSoundEffects(soundEffectsSelection.getText().equalsIgnoreCase("ON"));
            configuration.setAiPlay(aiPlaySelection.getText().equalsIgnoreCase("ON"));
            configuration.setExtendMode(extendModeSelection.getText().equalsIgnoreCase("ON"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
