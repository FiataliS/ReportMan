package com.fiatalis.windows.components.up;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class MenuBar extends JMenuBar {


    public MenuBar() {
        JPanel p = new JPanel(new GridLayout(1, 17));
        p.add(ButtonCreated.getInstance());
        p.add(Box.createGlue());
        p.add(ButtonDelete.getInstance());
        p.add(Box.createGlue());
        p.add(ButtonBack.getInstance());
        for (int i = 0; i < 8; i++) {
            p.add(Box.createGlue());
        }
        p.add(ButtonSave.getInstance());
        p.add(Box.createGlue());
        p.add(CheckBoxEditing.getInstance());
        this.add(p);
        this.setBorder(new BevelBorder(1));
    }
}
