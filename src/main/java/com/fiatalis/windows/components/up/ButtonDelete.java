package com.fiatalis.windows.components.up;

import com.fiatalis.utils.MessageUtils;
import com.fiatalis.windows.components.center.Table;

public class ButtonDelete extends ButtonMenuItem {

    private static volatile ButtonDelete instance;

    public static ButtonDelete getInstance() {
        ButtonDelete localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonDelete.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonDelete();
                }
            }
        }
        return localInstance;
    }

    public ButtonDelete() {
        super("buttonDelete.png", "Удалить");
    }

    @Override
    protected void action() {
        if (Table.getInstance().getSelectedRow() < 0) {
            alert("Ошибка!", "Строка не выбрана!");
            return;
        }
        if (MessageUtils.alertChoice("Внимание!", "Удаление будет не обратимо после сохранения!"))
            Table.getInstance().deleteRowEntity();
    }
}
