package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;
import com.fiatalis.windows.components.center.modelTable.ExecutorModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditing extends JMenuItem {

    private boolean isEditable = false;
    private static volatile ButtonEditing instance;

    public static ButtonEditing getInstance() {
        ButtonEditing localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonEditing.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonEditing();
                }
            }
        }
        return localInstance;
    }

    public ButtonEditing() {
        super();
        this.setBorder(new BevelBorder(0));
        this.setToolTipText("Разрешить/запретить редактирование");
        setIcon();
        listeners();
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEditable(!isEditable);
            }
        });
    }

    private void setIcon() {
        Image img;
        if (!isEditable) {
            img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonEditableLock.png"));
        } else {
            img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonEditableUnlock.png"));
        }
        this.setIcon(new ImageIcon(img));
        this.setHorizontalTextPosition(SwingConstants.RIGHT);
    }

    @Override
    public JToolTip createToolTip() {
        return new CustomJToolTip(this);
    }

    public void setEditable(boolean b) {
        isEditable = b;
        Table.getInstance().setEditableModel(b);
        if (Table.getInstance().getModel() instanceof ExecutorModel) ButtonBack.getInstance().setVisible(!b);
        setIcon();
    }
}
