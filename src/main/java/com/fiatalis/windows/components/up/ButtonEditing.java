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
        /////////////////////////////////////////////
        ButtonBack.getInstance().setVisible(false);
        ButtonBack.getInstance().setVisible(true);
        ButtonBack.getInstance().setVisible(false);
        ////Непонятно почему, но без изменения видимости кнопки не работает.
        if (Table.getInstance().getModel() instanceof ExecutorModel) ButtonBack.getInstance().setVisible(!isEditable);
        if (!isEditable) {
            super.icon = "buttonEditableLock.png";
            super.info = "Разрешить редактирование";
        } else {
            super.icon = "buttonEditableUnlock.png";
            super.info = "Запретить редактирование";
        }
        super.setting();
    }
}
