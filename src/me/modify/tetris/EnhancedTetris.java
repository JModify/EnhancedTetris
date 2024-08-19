package me.modify.tetris;

import com.sun.tools.javac.Main;
import me.modify.tetris.menu.MainMenu;

import javax.swing.*;

public class EnhancedTetris {
    public static void main(String[] args) {

        TetrisSplashScreen splashScreen = new TetrisSplashScreen(3000);
        splashScreen.showSplash();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Enhanced Tetris");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setResizable(false);
            frame.setIconImage(new ImageIcon("resources/icon.png").getImage());
            frame.setLocationRelativeTo(null);

            MainMenu mainMenu = new MainMenu(frame);
            mainMenu.load();

            frame.setVisible(true);
        });
    }

}
