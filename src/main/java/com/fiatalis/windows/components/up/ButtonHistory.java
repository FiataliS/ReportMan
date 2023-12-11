package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;
import lombok.Getter;

@Getter
public class ButtonHistory extends ButtonMenuItem {
    private static volatile ButtonHistory instance;

    public static ButtonHistory getInstance() {
        ButtonHistory localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonHistory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonHistory();
                }
            }
        }
        return localInstance;
    }

    public ButtonHistory() {
        super("buttonHistory.png", "Открыть историю");
    }

    @Override
    protected void action() {
        Table.getInstance().openCloseHistory();
        if (Table.getInstance().isHistory) {
            super.icon = "buttonHistory.png";
            super.info = "Открыть историю";
        } else {
            super.icon = "buttonHistoryClose.png";
            super.info = "Закрыть историю";
        }
        super.setting();
    }
}
