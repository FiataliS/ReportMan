package com.fiatalis.windows.components;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {


    public MenuBar() {
        JPanel p = new JPanel(new GridLayout(1, 17));
        p.add(CreatedButton.getInstance());
        p.add(Box.createGlue());
        p.add(DeleteButton.getInstance());
        p.add(Box.createGlue());
        p.add(BackButton.getInstance());
        for (int i = 0; i < 10; i++) {
            p.add(Box.createGlue());
        }
        p.add(EditableCheckBox.getInstance());
        this.add(p);
    }
}
