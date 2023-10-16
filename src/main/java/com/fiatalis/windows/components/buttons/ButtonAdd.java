package com.fiatalis.windows.components.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonAdd extends Buttons {

    public ButtonAdd() {
        this.setText("Новая задача");
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
