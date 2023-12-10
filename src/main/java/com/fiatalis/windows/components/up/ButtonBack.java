package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;

public class ButtonBack extends ButtonMenuItem {
    private static volatile ButtonBack instance;

    public static ButtonBack getInstance() {
        ButtonBack localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonBack.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonBack();
                }
            }
        }
        return localInstance;
    }

    public ButtonBack() {
        super("buttonBack.png", "Назад");
        this.setVisible(false);
    }

    @Override
    protected void action() {
        Table.getInstance().switchModel();
    }

}
