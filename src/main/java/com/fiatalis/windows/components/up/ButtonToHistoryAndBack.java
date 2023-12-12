package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;

public class ButtonToHistoryAndBack extends ButtonMenuItem {
    private static volatile ButtonToHistoryAndBack instance;

    public static ButtonToHistoryAndBack getInstance() {
        ButtonToHistoryAndBack localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonToHistoryAndBack.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonToHistoryAndBack();
                }
            }
        }
        return localInstance;
    }

    public ButtonToHistoryAndBack() {
        super("buttonComplete.png", "Завершить");
        setVisible(true);
    }

    @Override
    protected void action() {
        if (ButtonSave.getInstance().isVisible()) {
            alert("Запрещено", "Есть не сохраненные данные!");
            return;
        }
        if (Table.getInstance().getSelectedRow() < 0) {
            alert("Ошибка!", "Строка не выбрана!");
            return;
        }
        Table.getInstance().toHistoryAndBack();
        updateName();
        ButtonSave.getInstance().setVisible(false);
    }

    public void updateName() {
        if (Table.getInstance().isHistory) {
            icon = "buttonHistoryRestore.png";
            info = "Восстановить";
        } else {
            icon = "buttonComplete.png";
            info = "Завершить";
        }
        setting();
    }
}
