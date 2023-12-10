package com.fiatalis.windows.components.up;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class MenuBar extends JMenuBar {


    public MenuBar() {
        JPanel p = new JPanel(new GridLayout(1, 17));
        GridBagConstraints c = new GridBagConstraints();

        p.add(ButtonEditing.getInstance());
        p.add(ButtonCreated.getInstance());
        p.add(ButtonDelete.getInstance());
        for (int i = 0; i < 9; i++) {
            p.add(Box.createGlue());
        }
        p.add(ButtonSave.getInstance());
        p.add(ButtonNewFile.getInstance());
        p.add(ButtonOpenFile.getInstance());
        p.add(ButtonBack.getInstance());
        this.add(p);
        this.setBorder(new BevelBorder(1));
    }
}
