package com.fiatalis.windows.components.modelTable;

import com.fiatalis.CRUD.entytis.Entity;

import javax.swing.table.DefaultTableModel;

public abstract class Model extends DefaultTableModel {
    public String[] employee;
    public Boolean isEditable = false;

    public abstract void listeners();

    public abstract void update();

    public abstract void addRowEntity(Entity entity);

    public abstract void deleteRowEntity(int selectedRow);

    public abstract int getIndexColumn(Integer searchColumn);

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


}
