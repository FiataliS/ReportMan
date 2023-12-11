package com.fiatalis.utils;

import javax.swing.*;
import java.awt.*;

public class MessageUtils {

    public static void alert(String title, String message, String image, int messageType, int optionsType) {
        JDialog dialog = getPane(message, image, messageType, optionsType).createDialog(null, title);
        dialog.setVisible(true);
    }

    private static JOptionPane getPane(String message, String image, int messageType, int optionsType) {
        Image img = Toolkit.getDefaultToolkit().getImage(MessageUtils.getThis().getClass().getClassLoader().getResource("com.fiatalis/image/" + image));
        return new JOptionPane(message, messageType, optionsType, new ImageIcon(img), new String[]{"OK"});
    }

    private static MessageUtils getThis() {
        return new MessageUtils();
    }

    public static boolean alertChoice(String title, String message) {
        int choice = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION);
        if (choice == JOptionPane.OK_OPTION) return true;
        return false;
    }


}
