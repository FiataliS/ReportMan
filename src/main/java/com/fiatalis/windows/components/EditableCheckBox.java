package com.fiatalis.windows.components;

import com.fiatalis.windows.MainTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditableCheckBox extends JCheckBoxMenuItem {
    public EditableCheckBox() {
        super("Разрешить редактирование");
        listeners();
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (EditableCheckBox.this.getState()) {
                    MainTable.getInstance().setEditableModel(true);
                    ButtonBack.getInstance().setEnabled(false);
                } else {
                    MainTable.getInstance().setEditableModel(false);
                    ButtonBack.getInstance().setEnabled(true);
                }
            }
        });
    }
}
