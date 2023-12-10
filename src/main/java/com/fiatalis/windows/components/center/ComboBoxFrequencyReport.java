package com.fiatalis.windows.components.center;

import com.fiatalis.CRUD.Frequency;

import javax.swing.*;

public class ComboBoxFrequencyReport extends JComboBox {
    public ComboBoxFrequencyReport() {
        super();
        for (Frequency frequency : Frequency.values()) this.addItem(frequency.getName());
    }
}
