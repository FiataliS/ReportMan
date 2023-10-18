package com.fiatalis.windows.modelTable;

import com.fiatalis.CRUD.DAO.DAO;
import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.CRUD.entytis.Entity;
import com.fiatalis.CRUD.entytis.Executor;
import com.fiatalis.windows.components.ListReports;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class SecondModel extends Model {
    private final String[] employee = new String[]{"id", "Организация", "Ответственный", "Телефон", "Сдан отчет?"};
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
        super.employee = employee;
        listeners();
        update();
    }

    public void listeners() {
        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                ExecutorDAO rp = new ExecutorDAO();
                int row = e.getFirstRow();
                if (e.getColumn() == 4) {

                }
            }
        });
    }


    @Override
    public void update() {
        this.setRowCount(0);
        DAO rp = new ExecutorDAO();
        for (Entity e : rp.findAll()) {
            Executor r = (Executor) e;
            this.addRow(new Object[]{r.getId(), r.getName(), r.getResponsible(), r.getPhone()});
        }
    }

    @Override
    public void addRow(Entity entity) {
        DAO executorDAO = new ExecutorDAO();
        executorDAO.saveOrUpdate(entity);
    }

    @Override
    public void deleteRow() {
        DAO executorDAO = new ExecutorDAO();
        try {
            executorDAO.deleteById((Long) this.getValueAt(ListReports.getInstance().getSelectedRow(), 0));
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        update();
    }

}
