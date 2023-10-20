package com.fiatalis.windows.components;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        JPanel p = new JPanel(new GridLayout(1,4));
        p.add(new CreatedButton());
        p.add(new DeleteButton());
        p.add(Box.createGlue());
        p.add(new EditableCheckBox());
        this.add(p);
//        this.add(new CreatedButton());
//        this.add(Box.createHorizontalGlue());
//        this.add(new DeleteButton());
//        this.add(Box.createHorizontalGlue());
//        this.add(new EditableCheckBox());
    }
}
