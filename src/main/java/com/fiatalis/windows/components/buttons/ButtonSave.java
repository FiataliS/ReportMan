package com.fiatalis.windows.components.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonSave extends Buttons {

    public ButtonSave() {
        this.setText("Сохранить задачу");
    }

    public void listener() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
