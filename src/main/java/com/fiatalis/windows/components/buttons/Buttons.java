package com.fiatalis.windows.components.buttons;

import javax.swing.*;
import java.awt.*;

public abstract class Buttons extends JButton implements ButtonContract {
    public Buttons() {
        Font font = new Font("Verdana", Font.PLAIN, 14);
        this.setFont(font);
    }
}
