package com.fiatalis.windows.components.modelTable;

import com.fiatalis.CRUD.DAO.DAO;
import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.CRUD.entytis.Entity;
import com.fiatalis.CRUD.entytis.Reports;
import lombok.Data;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.sql.Date;

@Data
public class ReportModel extends Model {
    private final String[] employee = new String[]{"id", "Наименование", "Дата", "Периодичность", "Напоминание"};

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
        DAO rp = new ReportsDAO();
        for (Entity e : rp.findAll(null)) {
            Reports r = (Reports) e;
            this.addRow(new Object[]{r.getId(), r.getName(), r.getDateString(), r.getFrequency().getName(), r.getSubmitted()});
        }
    }

    public void listeners() {
        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                DAO rp = new ReportsDAO();
                Reports reports = null;
                int row = e.getFirstRow();
                if (e.getColumn() > 0 & e.getColumn() < 5)
                    reports = (Reports) rp.findById((Long) ReportModel.this.getValueAt(row, getIndexColumn(0)));
                switch (e.getColumn()) {
                    case 1:
                        reports.setName((String) ReportModel.this.getValueAt(row, getIndexColumn(1)));
                        break;
                    case 2:
                        reports.setDate(Date.valueOf((String) ReportModel.this.getValueAt(row, getIndexColumn(2))));
                        break;
                    case 3:
                        reports.setFrequencyInString((String) ReportModel.this.getValueAt(row, getIndexColumn(3)));
                        break;
                    case 4:
                        reports.setSubmitted((Boolean) ReportModel.this.getValueAt(row, getIndexColumn(4)));
                        break;
                }
                if (e.getColumn() > 0 & e.getColumn() < 5) rp.saveOrUpdate(reports);
            }
        });
    }

    @Override
    public void addRowEntity(Entity entity) {
        DAO reportsDAO = new ReportsDAO();
        reportsDAO.saveOrUpdate(entity);
    }

    @Override
    public void deleteRowEntity(int selectedRow) {
        DAO reportsDAO = new ReportsDAO();
        try {
            reportsDAO.deleteById((Long) this.getValueAt(selectedRow, 0));
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    @Override
    public int getIndexColumn(Integer searchColumn) {
        for (int i = 0; i < 5; i++) {
            if (this.getColumnName(i).equals(employee[searchColumn])) {
                return i;
            }
        }
        return -1;
    }
}
