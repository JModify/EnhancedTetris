package me.modify.tetris.listeners;

import me.modify.tetris.game.time.GameScheduler;

import javax.swing.*;
import java.awt.event.*;

public class ExitApplicationListener extends WindowAdapter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        exitApplication();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        exitApplication();
    }

    private void exitApplication() {
        int response = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            GameScheduler.getInstance().shutdown();
            System.exit(0);
        }
    }
}
