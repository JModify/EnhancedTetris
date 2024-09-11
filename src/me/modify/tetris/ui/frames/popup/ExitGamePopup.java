package me.modify.tetris.ui.frames.popup;

import javax.swing.*;
import java.awt.*;

public class ExitGamePopup extends PopupFrame {

    public ExitGamePopup() {
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
