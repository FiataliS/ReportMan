package com.fiatalis.windows.components.down;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class LabelInfo extends JLabel {

    private static volatile LabelInfo instance;

    public static LabelInfo getInstance() {
        LabelInfo localInstance = instance;
        if (localInstance == null) {
            synchronized (LabelInfo.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new LabelInfo();
                }
            }
        }
        return localInstance;
    }

    public LabelInfo() {
        this.setVisible(true);
        this.setBorder(new BevelBorder(1));
        Dimension newDim = new Dimension(625, 45);
        this.setMinimumSize(newDim);
        this.setPreferredSize(newDim);
        this.setMaximumSize(newDim);
        this.setSize(newDim);
        this.revalidate();
    }
}
