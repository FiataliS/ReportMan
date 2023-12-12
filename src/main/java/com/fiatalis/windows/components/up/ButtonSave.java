package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;

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
        Table.getInstance().save();
        this.setVisible(false);
    }
}
