package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;
import com.fiatalis.windows.components.center.modelTable.ExecutorModel;

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
    }

    @Override
    protected void action() {
        isEditable = !isEditable;
        Table.getInstance().setEditableModel(isEditable);
        if (Table.getInstance().getModel() instanceof ExecutorModel) ButtonBack.getInstance().setVisible(!isEditable);
        if (!isEditable) {
            super.icon = "buttonEditableLock.png";
            super.info = "Разрешить редактирование";
        } else {
            super.icon = "buttonEditableUnlock.png";
            super.info = "Запретить редактирование";
        }
        super.setting();
        this.revalidate();
    }
}
