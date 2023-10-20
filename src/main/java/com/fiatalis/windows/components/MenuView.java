package com.fiatalis.windows.components;

import javax.swing.*;

public class MenuView extends JMenu {
    JMenuItem createdView = new JMenuItem("Извенить вид");
    JMenuItem createdFront = new JMenuItem("Изменить шрифт");

    public MenuView() {
        super("Вид");
        this.add(createdView);
        this.add(new JPopupMenu.Separator());
        this.add(createdFront);
        this.add(new JPopupMenu.Separator());
        listeners();
    }

    private void listeners() {

    }
}
