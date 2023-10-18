package com.fiatalis.windows.modelTable;

import com.fiatalis.CRUD.entytis.Entity;

import javax.swing.table.DefaultTableModel;

public abstract class Model extends DefaultTableModel {
    public String[] employee;
    public Boolean isEditable = false;

    public abstract void listeners();

    public abstract void update();

    public abstract void addRow(Entity entity);

    public abstract void deleteRow();

    public void setEditableModel(boolean editableModel) {
        this.isEditable = editableModel;
    }

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

    public int getIndexColumn(Integer searchColumn) {
        for (int i = 0; i < 5; i++) {
            if (this.getColumnName(i).equals(employee[searchColumn])) {
                return i;
            }
        }
        return -1;
    }

}
