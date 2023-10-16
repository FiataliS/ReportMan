package com.fiatalis.windows.components;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.CRUD.DAO.ReportsDAOImpl;
import com.fiatalis.CRUD.entytis.Reports;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ListReports extends JTable {
    private final DefaultTableModel model;
    private final String[] employee = new String[]{"Наименование", "Дата", "Периодичность", "Напоминание"};

    private Object[][] array = new String[][]{{"Сахар", "кг", "1.5"},
            {"Мука", "кг", "4.0"},
            {"Молоко", "л", "2.2"}};


    public ListReports() {
        super();
        model = getTableModel();
        this.setModel(model);
        updateList();
        this.setAutoCreateRowSorter(true);
        listeners(this);
    }

    @SneakyThrows
    private void updateList() {
        ReportsDAO rp = new ReportsDAOImpl(ConnectDataBaseUtils.getInstance().getStmt());
        for (Reports r: rp.findAll()) {
            model.addRow(new Object[]{r.getName(), r.getDateString(), r.getFrequency(), r.getSubmitted()});
        }
        this.repaint();
    }


    private void listeners(JTable table) {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                List<Object> list = new ArrayList<>();
                if (e.getClickCount() == 2) {
                    try {
                        if (table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) instanceof Boolean) {
                        } else{
                            int index = table.getSelectedRow();
                            list.add(table.getValueAt(index, getIndexColumn(0)));
                            list.add(table.getValueAt(index, getIndexColumn(1)));
                            list.add(table.getValueAt(index, getIndexColumn(2)));
                            list.add(table.getValueAt(index, getIndexColumn(3)));
                            System.out.println(list);
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }

                }

            }
        });
    }

    private DefaultTableModel getTableModel() {
        return new DefaultTableModel() {

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
                if (this.getValueAt(row, column) instanceof Boolean) {
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (this.getValueAt(0, column) instanceof Boolean) {
            return Boolean.class;
        }
        return String.class;
    }

    private int getIndexColumn(Integer searchColumn) {
        for (int i = 0; i < 4; i++) {
            if (this.getColumnName(i).equals(employee[searchColumn])) {
                return i;
            }
        }
        return -1;
    }
}