package com.fiatalis.windows.components.up;

import javax.swing.*;
import java.awt.*;

public class CustomJToolTip extends JToolTip {
    public CustomJToolTip(JComponent component) {
        super();
        setComponent(component);
        setBackground(Color.YELLOW);
        setForeground(Color.BLACK);
    }
}
