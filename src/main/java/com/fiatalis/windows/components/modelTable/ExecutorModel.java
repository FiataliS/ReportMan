package com.fiatalis.windows.components.modelTable;

import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.CRUD.entytis.Entity;
import com.fiatalis.CRUD.entytis.Executor;
import com.fiatalis.CRUD.entytis.Reports;
import com.fiatalis.windows.components.NameLabel;
import com.fiatalis.windows.components.SaveButton;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;

public class ExecutorModel extends Model {
    private final String[] employee = new String[]{"id", "Организация", "Ответственный", "Телефон", "Сдан отчет?"};
    private long reportId;
    private final Reports reports;

    public ExecutorModel(Reports reports) {
        super.employee = employee;
        this.reports = reports;
        dao = new ExecutorDAO();
        if (reports.getId() != null) this.reportId = reports.getId();
        listeners();
        updateLabel();
        update();
    }

    @Override
    public void update() {
        this.setRowCount(0);
        this.setEntityListFromDataBase(dao.findAll(reportId));
        for (Entity e : entityListFromDataBase) {
            Executor r = (Executor) e;
            this.addRow(new Object[]{r.getId(), r.getName(), r.getResponsible(), r.getPhone()});
        }
    }

    public void listeners() {
        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (!ExecutorModel.this.getEntityListFromModel().equals(entityListFromDataBase)) {
                    SaveButton.getInstance().setVisible(true);
                } else {
                    SaveButton.getInstance().setVisible(false);
                }
            }
        });
    }

    @Override
    public void addRowEntity(Entity entity) {
        this.addRow(new Object[]{entity.getId(), null, null, null, null});
    }

    @Override
    public void deleteRowEntity(int selectedRow) {
        this.removeRow(selectedRow);
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

    @Override
    public List<Entity> getEntityListFromModel() {
        int countRow = this.getRowCount();
        List<Entity> list = new ArrayList<>();
        for (int i = 0; i < countRow; i++) {
            Executor executor = new Executor();
            executor.setId((Long) ExecutorModel.this.getValueAt(i, getIndexColumn(0)));
            executor.setIdReport(reportId);
            executor.setName((String) ExecutorModel.this.getValueAt(i, getIndexColumn(1)));
            executor.setResponsible((String) ExecutorModel.this.getValueAt(i, getIndexColumn(2)));
            executor.setPhone((String) ExecutorModel.this.getValueAt(i, getIndexColumn(3)));
            list.add(executor);
        }
        return list;
    }

    private void updateLabel() {
        NameLabel.getInstance().setText("Отчет: " + reports.getName() + ", до " + reports.getDateString());
        NameLabel.getInstance().setVisible(true);
    }
}