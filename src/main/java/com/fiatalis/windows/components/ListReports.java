package com.fiatalis.windows.components;

import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.CRUD.DAO.ReportsDAOImpl;
import com.fiatalis.CRUD.entytis.Reports;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ListReports extends JTable {
    private final DefaultTableModel model;
    private final String[] employee = new String[]{"id", "Наименование", "Дата", "Периодичность", "Напоминание"};
    private static volatile ListReports instance;
    public Boolean saveShow = false;
    public Boolean deleteShow = false;
    public Boolean editShow = true;
    public Boolean isEditable = false;


    public static ListReports getInstance() {
        ListReports localInstance = instance;
        if (localInstance == null) {
            synchronized (ListReports.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ListReports();
                }
            }
        }
        return localInstance;
    }

    public ListReports() {
        super();
        model = getTableModel();
        this.setModel(model);
        updateList();
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //this.setAutoCreateRowSorter(true);
        this.removeColumn(this.getColumnModel().getColumn(0));
        listeners(this);
    }

    @SneakyThrows
    public void updateList() {
        model.setRowCount(0);
        ReportsDAO rp = new ReportsDAOImpl();
        for (Reports r : rp.findAll()) {
            model.addRow(new Object[]{r.getId(), r.getName(), r.getDateString(), r.getFrequency(), r.getSubmitted()});
        }
    }


    private void listeners(JTable table) {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                List<Object> list = new ArrayList<>();
                if (e.getClickCount() == 2) {
                    try {
                        if (model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) instanceof Boolean) {
                        } else {
                            int row = table.getSelectedRow();
                            list.add(model.getValueAt(row, getIndexColumn(0)));
                            list.add(model.getValueAt(row, getIndexColumn(1)));
                            list.add(model.getValueAt(row, getIndexColumn(2)));
                            list.add(model.getValueAt(row, getIndexColumn(3)));
                            list.add(model.getValueAt(row, getIndexColumn(4)));
                            //System.out.println(list);
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                    }
                }
            }
        });
        this.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                ReportsDAOImpl rp = new ReportsDAOImpl();
                int row = e.getFirstRow();
                if (e.getColumn() == 4) {
                    Reports reports = rp.findById((Long) model.getValueAt(row, getIndexColumn(0)));
                    reports.setSubmitted((Boolean) model.getValueAt(row, getIndexColumn(4)));
                    rp.saveOrUpdate(reports);
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
                return isEditable;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                if (this.getValueAt(0, column) instanceof Boolean) {
                    return Boolean.class;
                }
                return String.class;
            }
        };
    }


    private int getIndexColumn(Integer searchColumn) {
        for (int i = 0; i < 5; i++) {
            if (this.getModel().getColumnName(i).equals(employee[searchColumn])) {
                return i;
            }
        }
        return -1;
    }

    public void deleteRow() {
        ReportsDAO reportsDAO = new ReportsDAOImpl();
        try {
            reportsDAO.deleteById((Long) model.getValueAt(this.getSelectedRow(), 0));
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        updateList();
    }

    public void addRow(Reports reports) {
        ReportsDAO reportsDAO = new ReportsDAOImpl();
        reportsDAO.saveOrUpdate(reports);
        updateList();
    }
}