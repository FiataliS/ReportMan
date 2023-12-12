package com.fiatalis.windows.components.up;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        JPanel p = new JPanel(new GridLayout(1, 17));
        p.add(ButtonEditing.getInstance());
        p.add(ButtonCreated.getInstance());
        p.add(ButtonDelete.getInstance());
        p.add(ButtonToHistoryAndBack.getInstance());
        p.add(ButtonOpenCloseHistory.getInstance());
        for (int i = 0; i < 11; i++) {
            p.add(Box.createGlue());
        }
        p.add(ButtonSave.getInstance());
        p.add(ButtonNewFile.getInstance());
        p.add(ButtonOpenFile.getInstance());
        p.add(ButtonBack.getInstance());
        this.add(p);
        this.revalidate();
        this.setBorder(new BevelBorder(1));
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.height = 45;
        return d;
    }
}
