package com.fiatalis.windows.components;

import com.fiatalis.windows.components.buttons.*;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        Buttons add = new ButtonAdd();
        Buttons delete = new ButtonDelete();
        Buttons save = new ButtonSave();
        Buttons edit = new ButtonEdit();
        this.add(Box.createHorizontalGlue());
        this.add(add, BorderLayout.CENTER);
        this.add(Box.createHorizontalGlue());
        this.add(delete);
        this.add(Box.createHorizontalGlue());
        this.add(save);
        this.add(Box.createHorizontalGlue());
        this.add(edit);
        this.add(Box.createHorizontalGlue());
    }
}
