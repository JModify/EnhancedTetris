package me.modify.tetris.listeners;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnMainMenuActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuFacade.openPanel(MenuType.MAIN_MENU);
    }

}
