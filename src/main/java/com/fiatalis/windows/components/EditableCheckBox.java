package com.fiatalis.windows.components;

import com.fiatalis.windows.MainTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditableCheckBox extends JCheckBoxMenuItem {

    private static volatile EditableCheckBox instance;

    public static EditableCheckBox getInstance() {
        EditableCheckBox localInstance = instance;
        if (localInstance == null) {
            synchronized (EditableCheckBox.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EditableCheckBox();
                }
            }
        }
        return localInstance;
    }

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
                    BackButton.getInstance().setEnabled(false);
                } else {
                    MainTable.getInstance().setEditableModel(false);
                    BackButton.getInstance().setEnabled(true);
                }
            }
        });
    }
}
