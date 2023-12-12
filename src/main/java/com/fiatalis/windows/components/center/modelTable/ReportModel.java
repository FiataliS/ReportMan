package com.fiatalis.windows.components.center.modelTable;

import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.entytis.Entity;
import com.fiatalis.entytis.Report;
import com.fiatalis.windows.components.up.ButtonSave;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;

public class ReportModel extends Model {
    private final String[] employee = new String[]{"id", "Наименование", "Дата", "Периодичность", "Напоминание", "Ссылка", "История"};
    private static volatile ReportModel instance;
    private boolean isHistory = false;

    public static ReportModel getInstance() {
        ReportModel localInstance = instance;
        if (localInstance == null) {
            synchronized (ReportModel.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ReportModel();
                }
            }
        }
        return localInstance;
    }

    public ReportModel() {
        super.employee = employee;
        dao = new ReportsDAO();
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
        List<Entity> list = new ArrayList<>();
        for (Entity e : dao.findAll(null)) {
            Report report = (Report) e;
            if (report.getHistory() == isHistory) {
                list.add(e);
            }
        }
        this.setEntityListFromDataBase(list);
        for (Entity e : entityListFromDataBase) {
            Report r = (Report) e;
            this.addRow(new Object[]{r.getId(), r.getName(), r.getDateString(), r.getFrequency().getName(), r.getSubmitted(), r.getLink(), r.getHistory()});
        }
    }

    public void listeners() {
        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (!ReportModel.this.getEntityListFromModel(isHistory).equals(entityListFromDataBase)) {
                    ButtonSave.getInstance().setVisible(true);
                } else {
                    ButtonSave.getInstance().setVisible(false);
                }
            }
        });
    }

    @Override
    public void addRowEntity(Entity entity) {
        this.addRow(new Object[]{entity.getId(), null, null, "Ежемесячно", false, null, false});
    }

    @Override
    public void deleteRowEntity(int selectedRow) {
        this.removeRow(selectedRow);
    }

    public void toHistoryAndBack(int selectedRow) {
        this.setValueAt(!isHistory, selectedRow, 6);
        this.setValueAt(false, selectedRow, 4);
        Long id = (Long) this.getValueAt(selectedRow, 0);
        List<Entity> reports = this.getEntityListFromModel(!isHistory);
        for (Entity e : reports) {
            if (e.getId() == id) {
                dao.saveOrUpdate(e);
            }
        }
        update(isHistory);
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

    public ArrayList<Entity> getEntityListFromModel(boolean isHistory) {
        int countRow = this.getRowCount();
        ArrayList<Entity> list = new ArrayList<>();
        for (int i = 0; i < countRow; i++) {
            Report report = new Report();
            report.setId((Long) this.getValueAt(i, getIndexColumn(0)));
            report.setName((String) this.getValueAt(i, getIndexColumn(1)));
            report.setDate(this.getValueAt(i, getIndexColumn(2)));
            report.setFrequencyFromString((String) this.getValueAt(i, getIndexColumn(3)));
            report.setSubmitted((Boolean) getValueAt(i, getIndexColumn(4)));
            report.setLink((String) getValueAt(i, getIndexColumn(5)));
            report.setHistory((Boolean) getValueAt(i, getIndexColumn(6)));
            if (report.getHistory() == isHistory) list.add(report);
        }
        return list;
    }
}
