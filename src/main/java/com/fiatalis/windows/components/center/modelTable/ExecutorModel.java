package com.fiatalis.windows.components.center.modelTable;

import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.entytis.Entity;
import com.fiatalis.entytis.Executor;
import com.fiatalis.entytis.Report;
import com.fiatalis.windows.components.center.Table;
import com.fiatalis.windows.components.up.ButtonSave;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;

public class ExecutorModel extends Model {
    private final String[] employee = new String[]{"id", "Организация", "Ответственный", "Телефон", "Готовность", "История"};
    private long reportId;
    private final Report report;
    private boolean isHistory = false;

    public long getReportId() {
        return reportId;
    }

    public ExecutorModel(Report report) {
        super.employee = employee;
        this.report = report;
        dao = new ExecutorDAO();
        if (report.getId() != null) this.reportId = report.getId();
        listeners();
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (this.getValueAt(0, column) instanceof Boolean) {
            return Boolean.class;
        }
        return String.class;
    }

    @Override
    public void update(boolean isHistory) {
        this.isHistory = isHistory;
        this.setRowCount(0);
        List<Entity> list = dao.findAll(reportId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHistory() != isHistory) {
                list.remove(i);
            }
        }
        this.setEntityListFromDataBase(list);
        for (Entity e : entityListFromDataBase) {
            Executor r = (Executor) e;
            if (r.getHistory() == Table.getInstance().isHistory) {
                this.addRow(new Object[]{r.getId(), r.getName(), r.getResponsible(), r.getPhone(), r.getSubmit(), r.getHistory()});
            }
        }
    }

    public void listeners() {
        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (!ExecutorModel.this.getEntityListFromModel(isHistory).equals(entityListFromDataBase)) {
                    ButtonSave.getInstance().setVisible(true);
                } else {
                    ButtonSave.getInstance().setVisible(false);
                }
            }
        });
    }

    @Override
    public void addRowEntity(Entity entity) {
        this.addRow(new Object[]{entity.getId(), null, null, null, false, false});
    }

    @Override
    public void deleteRowEntity(int selectedRow) {
        this.removeRow(selectedRow);
    }

    @Override
    public int getIndexColumn(Integer searchColumn) {
        for (int i = 0; i < this.getColumnCount(); i++) {
            if (this.getColumnName(i).equals(employee[searchColumn])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Entity> getEntityListFromModel(boolean isHistory) {
        int countRow = this.getRowCount();
        List<Entity> list = new ArrayList<>();
        for (int i = 0; i < countRow; i++) {
            Executor executor = new Executor();
            executor.setId((Long) this.getValueAt(i, getIndexColumn(0)));
            executor.setIdReport(reportId);
            executor.setName((String) this.getValueAt(i, getIndexColumn(1)));
            executor.setResponsible((String) this.getValueAt(i, getIndexColumn(2)));
            executor.setPhone((String) this.getValueAt(i, getIndexColumn(3)));
            executor.setSubmit((Boolean) this.getValueAt(i, getIndexColumn(4)));
            executor.setHistory((Boolean) this.getValueAt(i, getIndexColumn(5)));
            if (report.getHistory() == isHistory) list.add(executor);
        }
        return list;
    }
}