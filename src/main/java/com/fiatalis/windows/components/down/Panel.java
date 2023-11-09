package com.fiatalis.windows.components.down;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public Panel() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        this.add(LabelInfo.getInstance(), c);
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 32;
        c.ipadx = 32;
        this.add(ButtonNewFile.getInstance(), c);
        c.gridx = 2;
        c.gridy = 0;
        c.ipady = 32;
        c.ipadx = 32;
        this.add(ButtonOpenFile.getInstance(), c);
    }
}
