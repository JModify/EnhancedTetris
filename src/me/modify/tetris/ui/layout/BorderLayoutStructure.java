package me.modify.tetris.ui.layout;

import me.modify.tetris.ui.frames.MainFrame;
import me.modify.tetris.ui.panel.TPanel;

import javax.swing.*;
import java.awt.*;

public abstract class BorderLayoutStructure extends TPanel  {

    public BorderLayoutStructure(MainFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    public void paint() {
        SwingUtilities.invokeLater(() -> {
            setPanel(new JPanel(new BorderLayout()));

            panel.add(topPanel(), BorderLayout.NORTH);
            panel.add(leftPanel(), BorderLayout.WEST);
            panel.add(centerPanel(), BorderLayout.CENTER);
            panel.add(rightPanel(), BorderLayout.EAST);
            panel.add(bottomPanel(), BorderLayout.SOUTH);

            update();
        });
    }

    public abstract JPanel topPanel();

    public abstract JPanel leftPanel();

    public abstract JPanel centerPanel();

    public abstract JPanel rightPanel();

    public abstract JPanel bottomPanel();

}
