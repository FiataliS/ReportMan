package com.fiatalis.windows;

public class Controller {
    private static MainWindows windows;
    public static void startWindows() {
        windows = new MainWindows();
        windows.setVisible(true);
    }
}
