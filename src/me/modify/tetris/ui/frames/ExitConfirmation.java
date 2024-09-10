package me.modify.tetris.ui.frames;

import me.modify.tetris.EnhancedTetrisApp;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.synth.SynthButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitConfirmation extends PopupFrame {

    public ExitConfirmation() {
        super("Exit Confirmation");
    }

    @Override
    public void paint() {
        JLabel text = new JLabel("Are you sure you want to exit?");
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setBounds(0, 10, 300, 20);

        frame.add(text);
        frame.add(getYesButton(e -> System.exit(0)));
        frame.add(getNoButton(e -> frame.dispose()));

        frame.setVisible(true);
    }


}
