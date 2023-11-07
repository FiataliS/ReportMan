package com.fiatalis.windows.components.modelTable;

import com.fiatalis.CRUD.DAO.DAO;
import com.fiatalis.CRUD.entytis.Entity;
import lombok.Data;

import javax.swing.table.DefaultTableModel;
import java.util.List;

@Data
public abstract class Model extends DefaultTableModel {
    protected String[] employee;
    protected Boolean isEditable = false;
    protected List<Entity> entityListFromDataBase = null;
    protected DAO dao;


    public abstract void listeners();

    public abstract void update();

    public abstract void addRowEntity(Entity entity);

    public abstract void deleteRowEntity(int selectedRow);

    public abstract int getIndexColumn(Integer searchColumn);

    public void setEditableModel(boolean editableModel) {
        this.isEditable = editableModel;
    }

    public abstract List<Entity> getEntityListFromModel();

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
