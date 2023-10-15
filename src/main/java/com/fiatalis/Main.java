package com.fiatalis;

import com.fiatalis.windows.Controller;

import javax.swing.*;

public class Main {
    public static void main(String[] args)  {
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(() -> Controller.startWindows());
    }
}