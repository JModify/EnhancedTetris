package me.modify.tetris.ui.frames;

public class PopupFrameFactory {

    public static PopupFrame getPopupFrame(String title) {
        if (title.equalsIgnoreCase("Exit Confirmation")) return new ExitConfirmation();
        else if (title.equalsIgnoreCase("Stop Game")) return new GameQuitConfirmation();
        else return null;
    }

}
