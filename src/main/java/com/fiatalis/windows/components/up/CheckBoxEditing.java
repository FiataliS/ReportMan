package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;
import com.fiatalis.windows.components.center.modelTable.ExecutorModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CheckBoxEditing extends JMenuItem {

    private boolean isEditable = false;
    private static volatile CheckBoxEditing instance;

    public static CheckBoxEditing getInstance() {
        CheckBoxEditing localInstance = instance;
        if (localInstance == null) {
            synchronized (CheckBoxEditing.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CheckBoxEditing();
                }
            }
        }
        return localInstance;
    }

    public CheckBoxEditing() {
        super();
        this.setBorder(new BevelBorder(0));
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
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.add(new JLabel(new ImageIcon(img)), BorderLayout.CENTER);
//        this.add(panel);
    }

    public void setEditable(boolean b) {
        isEditable = b;
        Table.getInstance().setEditableModel(b);
        if (Table.getInstance().getModel() instanceof ExecutorModel) ButtonBack.getInstance().setVisible(!b);
        setIcon();
    }
}
