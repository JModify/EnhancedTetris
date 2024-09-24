package me.modify.tetris.ui.frames.popup;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class PopupFrame extends JFrame {

    public PopupFrame(String title) {
        super(title);
    }

    public abstract void init();

    public void open() {
        init();

        setLayout(null);
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
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
