package com.fiatalis.windows.modelTable;

import com.fiatalis.CRUD.DAO.DAO;
import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.CRUD.entytis.Entity;
import com.fiatalis.CRUD.entytis.Reports;
import com.fiatalis.windows.components.ListReports;
import lombok.Data;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

@Data
public class MainModel extends Model {
    private final String[] employee = new String[]{"id", "Наименование", "Дата", "Периодичность", "Напоминание"};

    private static volatile MainModel instance;

    public static MainModel getInstance() {
        MainModel localInstance = instance;
        if (localInstance == null) {
            synchronized (MainModel.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MainModel();
                }
            }
        }
        return localInstance;
    }

    public MainModel() {
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
            this.addRow(new Object[]{r.getId(), r.getName(), r.getDateString(), r.getFrequency(), r.getSubmitted()});
        }
    }

    public void listeners() {
        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                ReportsDAO rp = new ReportsDAO();
                int row = e.getFirstRow();
                if (e.getColumn() == 4) {
                    Reports reports = (Reports) rp.findById((Long) MainModel.this.getValueAt(row, getIndexColumn(0)));
                    reports.setSubmitted((Boolean) MainModel.this.getValueAt(row, getIndexColumn(4)));
                    rp.saveOrUpdate(reports);
                }
            }
        });
    }

    @Override
    public void addRow(Entity entity) {
        DAO reportsDAO = new ReportsDAO();
        reportsDAO.saveOrUpdate(entity);
    }

    @Override
    public void deleteRow() {
        DAO reportsDAO = new ReportsDAO();
        try {
            reportsDAO.deleteById((Long) this.getValueAt(ListReports.getInstance().getSelectedRow(), 0));
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }
}
