package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;
import lombok.Getter;

@Getter
public class ButtonOpenCloseHistory extends ButtonMenuItem {
    private static volatile ButtonOpenCloseHistory instance;

    public static ButtonOpenCloseHistory getInstance() {
        ButtonOpenCloseHistory localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonOpenCloseHistory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonOpenCloseHistory();
                }
            }
        }
        return localInstance;
    }

    public ButtonOpenCloseHistory() {
        super("buttonHistory.png", "Открыть историю");
        setVisible(true);
    }

    @Override
    protected void action() {
        if (ButtonSave.getInstance().isVisible()) {
            alert("Запрещено", "Есть не сохраненные данные!");
            return;
        }
        Table.getInstance().openCloseHistory();
        if (!Table.getInstance().isHistory) {
            icon = "buttonHistory.png";
            info = "Открыть историю";
            ButtonEditing.getInstance().setVisible(true);
        } else {
            icon = "buttonHistoryClose.png";
            info = "Закрыть историю";
            ButtonEditing.getInstance().setVisible(false);
        }
        ButtonToHistoryAndBack.getInstance().updateName();
        setting();
        ButtonSave.getInstance().setVisible(false);
    }
}
