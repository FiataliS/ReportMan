package com.fiatalis.windows.components;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditableChekBox extends JCheckBoxMenuItem {
    public EditableChekBox() {
        super("Разрешить редактирование");
        listeners();
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (EditableChekBox.this.getState()) {
                    ListReports.getInstance().isEditable = true;
                } else {
                    ListReports.getInstance().isEditable = false;
                }
            }
        });
    }
}
