package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;

public class ButtonCreated extends ButtonMenuItem {

    private static volatile ButtonCreated instance;

    public static ButtonCreated getInstance() {
        ButtonCreated localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonCreated.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonCreated();
                }
            }
        }
        return localInstance;
    }

    public ButtonCreated() {
        super("buttonCreate.png", "Добавить строку");
    }

    @Override
    protected void action() {
        Table.getInstance().addRowEntity();
    }
}
