package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;
import com.fiatalis.windows.components.center.modelTable.ExecutorModel;
import com.fiatalis.windows.components.center.modelTable.ReportModel;

public class ButtonEditing extends ButtonMenuItem {
    private boolean isEditable = false;
    private static volatile ButtonEditing instance;

    public static ButtonEditing getInstance() {
        ButtonEditing localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonEditing.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonEditing();
                }
            }
        }
        return localInstance;
    }

    public ButtonEditing() {
        super("buttonEditableLock.png", "Разрешить редактирование");
        setVisible(true);
    }

    @Override
    protected void action() {
        isEditable = !isEditable;
        Table.getInstance().setEditableModel(isEditable);
        ButtonCreated.getInstance().setVisible(isEditable);
        ButtonDelete.getInstance().setVisible(isEditable);
        ButtonNewFile.getInstance().setVisible(isEditable);
        if (Table.getInstance().getMyModel() instanceof ReportModel) {
            ButtonToHistoryAndBack.getInstance().setVisible(!isEditable);
            ButtonOpenCloseHistory.getInstance().setVisible(!isEditable);
        }
        if (Table.getInstance().getModel() instanceof ExecutorModel) {
            ButtonBack.getInstance().setVisible(!isEditable);
            ButtonNewFile.getInstance().setVisible(false);
        }
        if (!isEditable) {
            icon = "buttonEditableLock.png";
            info = "Разрешить редактирование";
        } else {
            icon = "buttonEditableUnlock.png";
            info = "Запретить редактирование";
        }
        setting();
    }
}
