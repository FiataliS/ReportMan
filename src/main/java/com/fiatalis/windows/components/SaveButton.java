package com.fiatalis.windows.components;

import com.fiatalis.CRUD.DAO.DAO;
import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.CRUD.entytis.Entity;
import com.fiatalis.windows.MainTable;
import com.fiatalis.windows.components.modelTable.Model;
import com.fiatalis.windows.components.modelTable.ReportModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SaveButton extends JMenuItem {
    private static volatile SaveButton instance;

    public static SaveButton getInstance() {
        SaveButton localInstance = instance;
        if (localInstance == null) {
            synchronized (SaveButton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SaveButton();
                }
            }
        }
        return localInstance;
    }

    public SaveButton() {
        super();
        this.setBorder(new BevelBorder(0));
        Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonSave.png"));
        this.setIcon(new ImageIcon(img));
        this.setHorizontalTextPosition(this.getSize().width / 2);
        this.setVisible(false);
        listeners();
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model model = (Model) MainTable.getInstance().getModel();
                if (model instanceof ReportModel) {
                    saveModel(model, new ReportsDAO());
                } else {
                    saveModel(model, new ExecutorDAO());
                }
                model.update();
                SaveButton.this.setVisible(false);
            }
        });
    }

    private void saveModel(Model model, DAO dao) {
        for (Entity entity : model.getEntityListFromModel()) dao.saveOrUpdate(entity);
        List<Long> dataBaseId = model.getEntityListFromDataBase().stream().map(Entity::getId).collect(Collectors.toList());
        List<Long> modelId = model.getEntityListFromModel().stream().map(Entity::getId).collect(Collectors.toList());
        dataBaseId.removeAll(modelId);
        for (Long id : dataBaseId) dao.deleteById(id);
    }
}
