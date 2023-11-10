package com.fiatalis;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
import com.fiatalis.windows.Controller;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ConnectDataBaseUtils.getInstance().connect();
        JFrame.setDefaultLookAndFeelDecorated(false);
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (UnsupportedLookAndFeelException e) {
//            throw new RuntimeException(e);
//        }
        javax.swing.SwingUtilities.invokeLater(() -> Controller.startWindows());
    }
}