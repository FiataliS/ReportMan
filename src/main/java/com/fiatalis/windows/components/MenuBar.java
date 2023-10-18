package com.fiatalis.windows.components;

import javax.swing.*;
import java.awt.*;


public class MenuBar extends JMenuBar {
    public MenuBar() {
        this.add(new MenuFile());
        this.add(new MenuView());
        this.add(Box.createHorizontalGlue());this.add(Box.createHorizontalGlue());this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());this.add(Box.createHorizontalGlue());this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());this.add(Box.createHorizontalGlue());this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());this.add(Box.createHorizontalGlue());this.add(Box.createHorizontalGlue());
        this.add(new EditableChekBox());
    }
}
