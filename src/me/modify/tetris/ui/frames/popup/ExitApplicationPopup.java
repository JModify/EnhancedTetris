package me.modify.tetris.ui.frames.popup;

import javax.swing.*;
import java.awt.*;

public class ExitApplicationPopup extends PopupFrame {

    public ExitApplicationPopup() {
        super("Exit Confirmation");
    }

    @Override
    public void init() {
        SwingUtilities.invokeLater(() -> {
            JLabel text = new JLabel("Are you sure you want to exit?");
            text.setAlignmentX(Component.CENTER_ALIGNMENT);
            text.setHorizontalAlignment(SwingConstants.CENTER);
            text.setBounds(0, 10, 300, 20);

            add(text);
            add(getYesButton(e -> System.exit(0)));
            add(getNoButton(e -> dispose()));

            setVisible(true);
        });
    }


}
