package com.fiatalis.windows.modelTable;

import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.CRUD.entytis.Reports;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class SecondModel extends Model {
    private final String[] employee = new String[]{"id", "Организация", "Ответственный", "Телефон", "Сдан отчет?"};
    private Boolean isEditable = false;


    private static volatile SecondModel instance;

    public static SecondModel getInstance() {
        SecondModel localInstance = instance;
        if (localInstance == null) {
            synchronized (SecondModel.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SecondModel();
                }
            }
        }
        return localInstance;
    }

    public SecondModel() {
        listeners();
        update();
    }

    private void listeners() {
        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                ReportsDAO rp = new ReportsDAO();
                int row = e.getFirstRow();
                if (e.getColumn() == 4) {
                    Reports reports = rp.findById((Long) SecondModel.this.getValueAt(row, getIndexColumn(0)));
                    reports.setSubmitted((Boolean) SecondModel.this.getValueAt(row, getIndexColumn(4)));
                    rp.saveOrUpdate(reports);
                }
            }
        });
    }


    @Override
    public void update() {

    }

    @Override
    public void addRow(Reports reports) {

    }

    @Override
    public void deleteRow() {

    }

    @Override
    public boolean getEditableModel() {
        return false;
    }

    @Override
    public void setEditableModel(boolean editableModel) {

    }
}
