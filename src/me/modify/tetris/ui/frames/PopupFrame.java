package me.modify.tetris.ui.frames;

import javax.swing.*;
import java.awt.*;

public abstract class PopupFrame {

    protected JFrame frame;
    public PopupFrame(String title) {
        this.frame = new JFrame(title);
    }

    public void open() {
        frame.setLayout(null);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        paint();
    }

    public abstract void paint();
}
