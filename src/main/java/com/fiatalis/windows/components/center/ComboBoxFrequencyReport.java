package com.fiatalis.windows.components.center;

import com.fiatalis.CRUD.Frequency;

import javax.swing.*;

public class ComboBoxFrequencyReport extends JComboBox {
    public ComboBoxFrequencyReport() {
        super();
        this.addItem(Frequency.Monthly.getName());
        this.addItem(Frequency.Quarterly.getName());
        this.addItem(Frequency.None.getName());
    }
}
