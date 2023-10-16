package com.fiatalis.windows.components.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonDelete extends Buttons {

    public ButtonDelete() {
        this.setText("Удалить задачу");
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
