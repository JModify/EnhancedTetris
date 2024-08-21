package me.modify.tetris.ui.panel;

import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.ui.MainFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel  extends TPanel {

    public GamePanel(MainFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    public void paint() {
        setPanel(new JPanel(new BorderLayout()));

        GameConfiguration configuration = getConfiguration();
        int rows = configuration.getFieldHeight();
        int columns = configuration.getFieldWidth();

        JPanel boardPanel = new JPanel(new GridLayout(rows,
                columns, 0, 0));
        //boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for(int i = 0; i < (rows * columns); i++) {
            JPanel cell = new JPanel(null);
            //cell.setBackground(new Color(0, 168, 255, 255));
            cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            boardPanel.add(cell);
        }

        JPanel emptyTop = new JPanel(null);
        emptyTop.setPreferredSize(new Dimension(700, 50));
        emptyTop.setBorder(BorderFactory.createLineBorder(Color.RED));

        JPanel emptyLeft = new JPanel(null);
        emptyLeft.setPreferredSize(new Dimension(225, 400));
        emptyLeft.setBorder(BorderFactory.createLineBorder(Color.RED));

        JPanel emptyRight = new JPanel(null);
        emptyRight.setPreferredSize(new Dimension(225, 400));
        emptyRight.setBorder(BorderFactory.createLineBorder(Color.RED));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(700, 50));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        JButton backButton = new JButton("Back");
        backButton.setUI(new BasicButtonUI());
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(backButton);
        backButton.addActionListener(e -> getMainFrame().openMainMenu());

        panel.add(boardPanel, BorderLayout.CENTER);

        panel.add(emptyTop, BorderLayout.NORTH);
        panel.add(emptyLeft, BorderLayout.WEST);
        panel.add(emptyRight, BorderLayout.EAST);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        update(panel);
    }

    public void colourCells() {

    }
}
