package com.fiatalis.windows.components;

import com.fiatalis.CRUD.Frequency;

import javax.swing.*;

public class FrequencyComboBox extends JComboBox {
    public FrequencyComboBox() {
        super();
        this.addItem(Frequency.Monthly.getName());
        this.addItem(Frequency.Quarterly.getName());
        this.addItem(Frequency.None.getName());
    }
}
