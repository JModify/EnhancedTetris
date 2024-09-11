package me.modify.tetris.ui.frames.popup;

public class PopupFrameFactory {

    public static PopupFrame getPopupFrame(String title) {

        if (title.equalsIgnoreCase("Exit Confirmation")) return new ExitGamePopup();

        else if (title.equalsIgnoreCase("Stop Game")) return new GameQuitPopup();

        else return null;
    }

}
