package com.fiatalis.windows.components.center.modelTable;

import com.fiatalis.utils.MonitoringUtils;
import com.fiatalis.windows.components.center.Table;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class LineWrapCellRenderer extends JTextArea implements TableCellRenderer {
    private ArrayList<Color> colors;

    public LineWrapCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        colorsInit(table);
        setText((value == null) ? "" : value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
        if (table.getRowHeight(row) != getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height);
        }
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            if (table.getModel() instanceof ReportModel) {
                setBackground(table.getSelectionBackground());
                if (column == 1) setBackground(colors.get(row));
            } else if (table.getModel() instanceof ExecutorModel) {
                setBackground(table.getSelectionBackground());
            }
        } else {
            setForeground(table.getForeground());
            if (table.getModel() instanceof ReportModel) {
                setBackground(colors.get(row));
            } else {
                setBackground(table.getBackground());
            }
        }
        return this;
    }

    private void colorsInit(JTable table) {
        if (table.getModel() instanceof ReportModel) {
            this.colors = new MonitoringUtils().managerColors(((ReportModel) table.getModel()).getEntityListFromModel(((Table)table).isHistory));
        }
    }
}


