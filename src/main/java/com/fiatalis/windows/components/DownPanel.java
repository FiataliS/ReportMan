package com.fiatalis.windows.components;

import javax.swing.*;
import java.awt.*;

public class DownPanel extends JPanel {
    public DownPanel() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        this.add(NameLabel.getInstance(), c);
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 32;
        c.ipadx = 32;
        this.add(AddFileButton.getInstance(), c);
        c.gridx = 2;
        c.gridy = 0;
        c.ipady = 32;
        c.ipadx = 32;
        this.add(OpenFileButton.getInstance(), c);
    }
}
