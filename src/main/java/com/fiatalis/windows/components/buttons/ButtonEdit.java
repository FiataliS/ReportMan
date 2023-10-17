package com.fiatalis.windows.components.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonEdit extends Buttons {

    public ButtonEdit() {
        this.setText("Изменить задачу");
        listener();
    }

    @Override
    public void listener() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
