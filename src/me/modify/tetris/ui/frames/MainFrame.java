package me.modify.tetris.ui.frames;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.listeners.GameKeyInputListener;
import me.modify.tetris.scores.HighScoresFile;
import me.modify.tetris.ui.panel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends JFrame{

    private GameKeyInputListener gameKeyInputListener;
    private JPanel allPanels;

    public MainFrame() {
        super("Enhanced Tetris");
        this.allPanels = new JPanel(new CardLayout());
        this.gameKeyInputListener = new GameKeyInputListener(EnhancedTetrisApp.getInstance().getGameController());
    }

    public void init() {
        SwingUtilities.invokeLater(() -> {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int response = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to exit?",
                            "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        EnhancedTetrisApp.getInstance().saveDataFiles();
                        System.exit(0);
                    }
                }
            });

            setSize(700, 500);
            setResizable(false);
            setIconImage(new ImageIcon("resources/icon.png").getImage());
            addKeyListener(gameKeyInputListener);

//            allPanels.setSize(700, 500);
            allPanels.add(new MainMenuPanel(), "Main_Menu_Panel");
            allPanels.add(new ConfigurationPanel(), "Configuration_Panel");
            allPanels.add(new HighScoresPanel(), "High_Scores_Panel");
            allPanels.add(new GamePanel(), "Game_Panel");
            add(allPanels);

            // Centers Main Frame to open in the center of the screen.
            setLocationRelativeTo(null);
            setVisible(true);
        });
    }

    public void restoreSizeDefault() {
        setSize(700, 500);
        revalidate();
        repaint();

        // Reposition frame to center
        setLocationRelativeTo(null);
    }

    public void resizeFrame(int width, int height) {
        setSize(width, height);
        revalidate();
        repaint();

        // Reposition frame to center
        setLocationRelativeTo(null);
    }

    public JPanel getAllPanels() {
        return this.allPanels;
    }

    public GameKeyInputListener getMovementListener() {
        return gameKeyInputListener;
    }
}
