package com.fiatalis.windows.components.center;

import com.fiatalis.CRUD.DAO.DAO;
import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.entytis.Entity;
import com.fiatalis.entytis.Executor;
import com.fiatalis.entytis.Report;
import com.fiatalis.utils.MessageUtils;
import com.fiatalis.windows.components.center.modelTable.LineWrapCellRenderer;
import com.fiatalis.windows.components.center.modelTable.Model;
import com.fiatalis.windows.components.up.ButtonBack;
import com.fiatalis.windows.components.center.modelTable.ReportModel;
import com.fiatalis.windows.components.center.modelTable.ExecutorModel;
import com.fiatalis.windows.components.up.ButtonNewFile;
import com.fiatalis.windows.components.up.ButtonOpenFile;
import com.fiatalis.windows.components.up.ButtonSave;
import org.jdesktop.swingx.table.DatePickerCellEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Table extends JTable {
    private static volatile Table instance;
    public boolean isHistory = false;

    public static Table getInstance() {
        Table localInstance = instance;
        if (localInstance == null) {
            synchronized (Table.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Table();
                }
            }
        }
        return localInstance;
    }

    public Table() {
        listeners();
        this.setModel(ReportModel.getInstance());
        this.getMyModel().update(isHistory);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        optionTableReport();
    }

    public void setColorRow() {
        this.setDefaultRenderer(Object.class, new LineWrapCellRenderer());
        this.getInstance().repaint();
    }

    private void listeners() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !ReportModel.getInstance().getIsEditable()) {
                    if (ButtonSave.getInstance().isVisible()) {
                        MessageUtils.alert("Ошибка!", "Нельзя продолжить работу не сохранив!","deleteRowFiled.png",JOptionPane.WARNING_MESSAGE,JOptionPane.OK_OPTION);
                        return;
                    }
                    if (Table.this.getModel() instanceof ReportModel) switchModel();
                }
            }
        });
    }

    public void setEditableModel(Boolean editableModel) {
        this.getMyModel().setEditableModel(editableModel);
    }

    public void deleteRowEntity() {
        this.getMyModel().deleteRowEntity(this.getSelectedRow());
    }

    public void toHistory() {
        this.getMyModel().toHistory(this.getSelectedRow());
    }

    public void openCloseHistory() {
        isHistory = !isHistory;
        this.getMyModel().update(isHistory);
    }

    public void save() {
        if (this.getMyModel() instanceof ReportModel) {
            saveModel(this.getMyModel(), new ReportsDAO());
        } else {
            saveModel(this.getMyModel(), new ExecutorDAO());
        }
        this.getMyModel().update(isHistory);
        Table.getInstance().setColorRow();
    }

    private void saveModel(Model model, DAO dao) {
        for (Entity entity : model.getEntityListFromModel(isHistory))
            if (entity.getName() != null) dao.saveOrUpdate(entity);
        List<Long> dataBaseId = new ArrayList<>();

        for (Entity entity : model.getEntityListFromDataBase()) {
            if (entity.getHistory() == isHistory) {
                Long entity1Id = entity.getId();
                dataBaseId.add(entity1Id);
            }
        }

        List<Long> modelId = new ArrayList<>();
        for (Entity entity : model.getEntityListFromModel(isHistory)) {
            Long entityId = entity.getId();
            modelId.add(entityId);
        }
        dataBaseId.removeAll(modelId);
        for (Long id : dataBaseId) dao.deleteById(id);
    }

    public String getFileFromReport() {
        if (this.getMyModel() instanceof ReportModel)
            return (String) this.getMyModel().getValueAt(this.getSelectedRow(), 5);
        return "";
    }

    public void setFileURL(String url) {
        if (this.getMyModel() instanceof ReportModel) this.getMyModel().setValueAt(url, this.getSelectedRow(), 5);
    }

    public void addRowEntity() {
        if (this.getModel() instanceof ReportModel) {
            ReportModel r = (ReportModel) this.getModel();
            Report report = new Report();
            report.setFrequency(null);
            r.addRowEntity(report);
        } else {
            ExecutorModel s = (ExecutorModel) this.getModel();
            s.addRowEntity(new Executor());
        }
    }

    public void switchModel() {
        if (Table.getInstance().getModel() instanceof ReportModel) {
            this.setModel(new ExecutorModel((Report) ReportModel.getInstance().getEntityListFromModel(isHistory).get(Table.getInstance().getSelectedRow())));
            optionTableExecutor();
            ButtonBack.getInstance().setVisible(true);
            ButtonOpenFile.getInstance().setVisible(false);
        } else if (Table.getInstance().getModel() instanceof ExecutorModel) {
            this.setModel(ReportModel.getInstance());
            optionTableReport();
            ButtonBack.getInstance().setVisible(false);
            ButtonOpenFile.getInstance().setVisible(true);
        }
        this.getMyModel().update(isHistory);
        this.repaint();
    }

    private void optionTableExecutor() {
        this.removeColumn(this.getColumnModel().getColumn(0));
        this.removeColumn(this.getColumnModel().getColumn(4));
        this.getColumnModel().getColumn(0).setCellRenderer(new LineWrapCellRenderer());
        this.getColumnModel().getColumn(1).setCellRenderer(new LineWrapCellRenderer());
        this.getColumnModel().getColumn(1).setMaxWidth(300);
        this.getColumnModel().getColumn(1).setMinWidth(100);
        this.getColumnModel().getColumn(1).setResizable(false);
        this.getColumnModel().getColumn(2).setMaxWidth(150);
        this.getColumnModel().getColumn(2).setMinWidth(150);
        this.getColumnModel().getColumn(2).setResizable(false);
        this.getColumnModel().getColumn(3).setMaxWidth(100);
        this.getColumnModel().getColumn(3).setMinWidth(100);
        this.getColumnModel().getColumn(3).setResizable(false);

    }

    private void optionTableReport() {
        this.removeColumn(this.getColumnModel().getColumn(0));
        this.removeColumn(this.getColumnModel().getColumn(4));
        this.removeColumn(this.getColumnModel().getColumn(4));
        DatePickerCellEditor datePickerCellEditor = new DatePickerCellEditor();
        datePickerCellEditor.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        this.getColumnModel().getColumn(1).setCellEditor(datePickerCellEditor);
        this.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new ComboBoxFrequencyReport()));
        //this.getColumnModel().getColumn(0).setCellRenderer(new LineWrapCellRenderer());
        this.getColumnModel().getColumn(1).setMaxWidth(70);
        this.getColumnModel().getColumn(1).setMinWidth(70);
        this.getColumnModel().getColumn(1).setResizable(false);
        this.getColumnModel().getColumn(2).setMaxWidth(100);
        this.getColumnModel().getColumn(2).setMinWidth(100);
        this.getColumnModel().getColumn(2).setResizable(false);
        this.getColumnModel().getColumn(3).setMaxWidth(100);
        this.getColumnModel().getColumn(3).setMinWidth(100);
        this.getColumnModel().getColumn(3).setResizable(false);
        this.setDefaultRenderer(Object.class, new LineWrapCellRenderer());
    }

    public Model getMyModel() {
        return (Model) super.getModel();
    }
}

