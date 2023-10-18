package com.fiatalis.windows.modelTable;

import com.fiatalis.CRUD.entytis.Reports;

import javax.swing.table.DefaultTableModel;

public abstract class Model extends DefaultTableModel {
    public String[] employee;
    public abstract void update();
    public abstract void addRow(Reports reports);
    public abstract void deleteRow();
    public abstract boolean getEditableModel() ;
    public abstract void setEditableModel(boolean editableModel);

    public int getIndexColumn(Integer searchColumn) {
        for (int i = 0; i < 5; i++) {
            if (this.getColumnName(i).equals(employee[searchColumn])) {
                return i;
            }
        }
        return -1;
    }
}
