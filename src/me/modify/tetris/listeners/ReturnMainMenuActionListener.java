package me.modify.tetris.listeners;

import me.modify.tetris.EnhancedTetrisApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnMainMenuActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        EnhancedTetrisApp.getInstance().getMainFrame().openMainMenu();
    }

}
