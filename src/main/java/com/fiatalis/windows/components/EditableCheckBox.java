package com.fiatalis.windows.components;

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
                    ListReports.getInstance().setEditableModel(true);
                    ButtonBack.getInstance().setEnabled(false);
                } else {
                    ListReports.getInstance().setEditableModel(false);
                    ButtonBack.getInstance().setEnabled(true);
                }
            }
        });
    }
}
