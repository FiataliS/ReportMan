package com.fiatalis.windows.components.center.modelTable;

import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.entytis.Entity;
import com.fiatalis.entytis.Reports;
import com.fiatalis.windows.components.up.ButtonSave;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;

public class ReportModel extends Model {
    private final String[] employee = new String[]{"id", "Наименование", "Дата", "Периодичность", "Напоминание", "Ссылка", "История"};
    private static volatile ReportModel instance;

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
        update();
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (this.getValueAt(0, column) instanceof Boolean) {
            return Boolean.class;
        }
        return String.class;
    }

    @Override
    public void update() {
        this.setRowCount(0);
        this.setEntityListFromDataBase(dao.findAll(null));
        for (Entity e : entityListFromDataBase) {
            Reports r = (Reports) e;
            this.addRow(new Object[]{r.getId(), r.getName(), r.getDateString(), r.getFrequency().getName(), r.getSubmitted(), r.getLink(), r.getHistory()});
        }
    }

    public void listeners() {
        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (!ReportModel.this.getEntityListFromModel().equals(entityListFromDataBase)) {
                    ButtonSave.getInstance().setVisible(true);
                } else {
                    ButtonSave.getInstance().setVisible(false);
                }
            }
        });
    }

    @Override
    public void addRowEntity(Entity entity) {
        this.addRow(new Object[]{entity.getId(), null, null, "Нет", false, null, false});
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
    public List<Entity> getEntityListFromModel() {
        int countRow = this.getRowCount();
        List<Entity> list = new ArrayList<>();
        for (int i = 0; i < countRow; i++) {
            Reports reports = new Reports();
            reports.setId((Long) this.getValueAt(i, getIndexColumn(0)));
            reports.setName((String) this.getValueAt(i, getIndexColumn(1)));
            reports.setDate(this.getValueAt(i, getIndexColumn(2)));
            reports.setFrequencyInString((String) this.getValueAt(i, getIndexColumn(3)));
            reports.setSubmitted((Boolean) getValueAt(i, getIndexColumn(4)));
            reports.setLink((String) getValueAt(i, getIndexColumn(5)));
            reports.setHistory((Boolean) getValueAt(i, getIndexColumn(6)));
            list.add(reports);
        }
        return list;
    }
}
