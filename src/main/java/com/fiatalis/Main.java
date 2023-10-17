package com.fiatalis;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
import com.fiatalis.windows.Controller;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ConnectDataBaseUtils.getInstance().connect();
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(() -> Controller.startWindows());
    }
}