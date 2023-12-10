package com.fiatalis.windows.components.up;

import com.fiatalis.CRUD.DAO.DAO;
import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.entytis.Entity;
import com.fiatalis.windows.components.center.Table;
import com.fiatalis.windows.components.center.modelTable.Model;
import com.fiatalis.windows.components.center.modelTable.ReportModel;

import java.util.List;
import java.util.stream.Collectors;


public class ButtonSave extends ButtonMenuItem {
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
        super("buttonSave.png", "Сохранить");
        this.setVisible(false);
    }


    @Override
    protected void action() {
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

    private void saveModel(Model model, DAO dao) {
        for (Entity entity : model.getEntityListFromModel()) if (entity.getName() != null) dao.saveOrUpdate(entity);
        List<Long> dataBaseId = model.getEntityListFromDataBase().stream().map(Entity::getId).collect(Collectors.toList());
        List<Long> modelId = model.getEntityListFromModel().stream().map(Entity::getId).collect(Collectors.toList());
        dataBaseId.removeAll(modelId);
        for (Long id : dataBaseId) dao.deleteById(id);
    }
}
