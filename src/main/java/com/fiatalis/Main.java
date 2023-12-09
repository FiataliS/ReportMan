package com.fiatalis;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
import com.fiatalis.windows.Controller;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ConnectDataBaseUtils.getInstance().connect();
        JFrame.setDefaultLookAndFeelDecorated(false);
        javax.swing.SwingUtilities.invokeLater(() -> Controller.startWindows());
       // MonitoringUtils.start();
    }
}