package com.fiatalis.windows.components.modelTable;

import com.fiatalis.CRUD.DAO.DAO;
import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.CRUD.entytis.Entity;
import com.fiatalis.CRUD.entytis.Executor;
import com.fiatalis.windows.MainTable;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class SecondModel extends Model {
    private final String[] employee = new String[]{"id", "Организация", "Ответственный", "Телефон", "Сдан отчет?"};
    private static volatile SecondModel instance;
    private long reportId;

    public static SecondModel getInstance(Long reportId) {
        SecondModel localInstance = instance;
        if (localInstance == null) {
            synchronized (SecondModel.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SecondModel();
                }
            }
        }
        if (reportId != null) localInstance.reportId = reportId;
        localInstance.update();
        return localInstance;
    }

    public SecondModel() {
        super.employee = employee;
        listeners();
    }

    public void listeners() {
        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                DAO executorDAO = new ExecutorDAO();
                Executor executor = null;
                int row = e.getFirstRow();
                if (e.getColumn() > 0 & e.getColumn() < 4)
                    executor = (Executor) executorDAO.findById((Long) SecondModel.this.getValueAt(row, getIndexColumn(0)));
                switch (e.getColumn()) {
                    case 1:
                        executor.setName((String) SecondModel.this.getValueAt(row, getIndexColumn(1)));
                        break;
                    case 2:
                        executor.setResponsible((String) SecondModel.this.getValueAt(row, getIndexColumn(2)));
                        break;
                    case 3:
                        executor.setPhone((String) SecondModel.this.getValueAt(row, getIndexColumn(3)));
                        break;
                }
                if (e.getColumn() > 0 & e.getColumn() < 4) executorDAO.saveOrUpdate(executor);
            }
        });
    }

    @Override
    public void update() {
        this.setRowCount(0);
        DAO rp = new ExecutorDAO();
        for (Entity e : rp.findAll(reportId)) {
            Executor r = (Executor) e;
            this.addRow(new Object[]{r.getId(), r.getName(), r.getResponsible(), r.getPhone()});
        }
    }

    @Override
    public void addRowEntity(Entity entity) {
        DAO executorDAO = new ExecutorDAO();
        Executor e = (Executor) entity;
        e.setIdReport(reportId);
        executorDAO.saveOrUpdate(e);
    }

    @Override
    public void deleteRowEntity(int selectedRow) {
        DAO executorDAO = new ExecutorDAO();
        try {
            executorDAO.deleteById((Long) this.getValueAt(Integer.valueOf(selectedRow), 0));
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    @Override
    public int getIndexColumn(Integer searchColumn) {
        for (int i = 0; i < 4; i++) {
            if (this.getColumnName(i).equals(employee[searchColumn])) {
                return i;
            }
        }
        return -1;
    }

}
