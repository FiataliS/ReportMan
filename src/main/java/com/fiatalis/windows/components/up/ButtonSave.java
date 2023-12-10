package com.fiatalis.windows.components.up;

import com.fiatalis.CRUD.DAO.DAO;
import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.entytis.Entity;
import com.fiatalis.windows.components.center.Table;
import com.fiatalis.windows.components.center.modelTable.Model;
import com.fiatalis.windows.components.center.modelTable.ReportModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;


public class ButtonSave extends JMenuItem {
    private static volatile ButtonSave instance;

    public static ButtonSave getInstance() {
        ButtonSave localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonSave.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonSave();
                }
            }
        }
        return localInstance;
    }

    public ButtonSave() {
        super();
        this.setBorder(new BevelBorder(0));
        this.setToolTipText("Сохранить");
        Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonSave.png"));
        this.setIcon(new ImageIcon(img));
        this.setHorizontalTextPosition(this.getSize().width / 2);
        this.setVisible(false);
        listeners();
    }

    @Override
    public JToolTip createToolTip() {
        return new CustomJToolTip(this);
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model model = (Model) Table.getInstance().getModel();
                if (model instanceof ReportModel) {
                    saveModel(model, new ReportsDAO());
                } else {
                    saveModel(model, new ExecutorDAO());
                }
                model.update();
                ButtonSave.this.setVisible(false);
                Table.getInstance().setColorRow();
            }
        });
    }

    private void saveModel(Model model, DAO dao) {
        for (Entity entity : model.getEntityListFromModel()) if (entity.getName() != null) dao.saveOrUpdate(entity);
        List<Long> dataBaseId = model.getEntityListFromDataBase().stream().map(Entity::getId).collect(Collectors.toList());
        List<Long> modelId = model.getEntityListFromModel().stream().map(Entity::getId).collect(Collectors.toList());
        dataBaseId.removeAll(modelId);
        for (Long id : dataBaseId) dao.deleteById(id);
    }
}
