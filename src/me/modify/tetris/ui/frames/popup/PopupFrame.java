package me.modify.tetris.ui.frames.popup;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class PopupFrame {

    protected JFrame frame;
    public PopupFrame(String title) {
        this.frame = new JFrame(title);
    }

    public abstract void paint();

    public void open() {
        frame.setLayout(null);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        paint();
    }

    protected JButton getYesButton(ActionListener actionListener) {
        JButton yesButton = new JButton("Yes");
        yesButton.setUI(new BasicButtonUI());
        yesButton.setBounds(50, 50, 75, 40);
        yesButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        yesButton.setBackground(Color.WHITE);
        yesButton.addActionListener(actionListener);
        return yesButton;
    }

    protected JButton getNoButton(ActionListener actionListener) {
        JButton noButton = new JButton("No");
        noButton.setUI(new BasicButtonUI());
        noButton.setBounds(160, 50, 75, 40);
        noButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        noButton.setBackground(Color.WHITE);
        noButton.addActionListener(actionListener);
        return noButton;
    }
}
