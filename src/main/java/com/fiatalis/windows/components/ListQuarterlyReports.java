package com.fiatalis.windows.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class ListQuarterlyReports extends JTable {
    DefaultTableModel model;

    private Object[][] array = new String[][]{{"Сахар", "кг", "1.5"},
            {"Мука", "кг", "4.0"},
            {"Молоко", "л", "2.2"}};


    public ListQuarterlyReports() {
        super();
        model = getTableModel();
        this.setModel(model);
        addSentetic();
        //this.setAutoCreateRowSorter(true);
        listeners(this);
    }

    private void addSentetic() {
        model.addRow(new Object[]{"Отчет о социально экономическом развитии", "20.11.2023", "Ежеквартально", new Boolean(false)});
        model.addRow(new Object[]{"Отчет о социально экономическом развитии", "20.10.2023", "Ежеквартально", new Boolean(false)});
        model.addRow(new Object[]{"Отчет о социально экономическом развитии", "20.11.2023", "Ежеквартально", new Boolean(false)});
        this.repaint();
    }


    private void listeners(JTable table) {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                List<Object> list = new ArrayList<>();
                if (e.getClickCount() == 2) {
                    if (table.getSelectedColumn() != 3) {
                        int index = table.getSelectedRow();
                        list.add(table.getValueAt(index, 0));
                        list.add(table.getValueAt(index, 1));
                        list.add(table.getValueAt(index, 2));
                        list.add(table.getValueAt(index, 3));
                        System.out.println(list);
                    }
                }

            }
        });
    }

    private DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            String[] employee = new String[]{"Наименование", "Дата", "Периодичность", "Напоминание"};

            @Override
            public int getColumnCount() {
                return employee.length;
            }

            @Override
            public String getColumnName(int index) {
                return employee[index];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                switch (column) {
                    case 0:
                    case 1:
                    case 2:
                        return false;
                    default:
                        return true;
                }
            }
        };
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
            case 1:
            case 2:
                return String.class;
            default:
                return Boolean.class;
        }
    }

}