package com.fiatalis.windows.components;

import com.fiatalis.windows.MainTable;
import com.fiatalis.windows.components.modelTable.ExecutorModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditableCheckBox extends JMenuItem {

    private boolean isEditable = false;
    private static volatile EditableCheckBox instance;

    public static EditableCheckBox getInstance() {
        EditableCheckBox localInstance = instance;
        if (localInstance == null) {
            synchronized (EditableCheckBox.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EditableCheckBox();
                }
            }
        }
        return localInstance;
    }

    public EditableCheckBox() {
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
        MainTable.getInstance().setEditableModel(b);
        if (MainTable.getInstance().getModel() instanceof ExecutorModel) BackButton.getInstance().setVisible(!b);
        setIcon();
    }
}
