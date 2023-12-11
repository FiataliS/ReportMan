package com.fiatalis.windows.components.center.modelTable;

import com.fiatalis.CRUD.DAO.DAO;
import com.fiatalis.entytis.Entity;
import lombok.Data;

import javax.swing.table.DefaultTableModel;
import java.util.List;

@Data
public abstract class Model extends DefaultTableModel {
    String[] employee;
    Boolean isEditable = false;
    List<Entity> entityListFromDataBase = null;
    DAO dao;


    public abstract void listeners();

    public abstract void update(boolean isHistory);

    public abstract void addRowEntity(Entity entity);

    public abstract void deleteRowEntity(int selectedRow);

    public abstract void toHistory(int selectedRow);

    public abstract int getIndexColumn(Integer searchColumn);

    public void setEditableModel(boolean editableModel) {
        this.isEditable = editableModel;
    }

    public abstract List<Entity> getEntityListFromModel(boolean isHistory);

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
