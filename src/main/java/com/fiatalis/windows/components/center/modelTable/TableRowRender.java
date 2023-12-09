package com.fiatalis.windows.components.center.modelTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class TableRowRender extends DefaultTableCellRenderer {
    private ArrayList<Color> colors;

    public TableRowRender(ArrayList<Color> colors) {
        this.colors = colors;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        component.setBackground(colors.get(row));
        return component;
    }
}
