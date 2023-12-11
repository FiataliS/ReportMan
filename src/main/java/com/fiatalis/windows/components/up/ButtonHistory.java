package com.fiatalis.windows.components.up;

public class ButtonHistory extends ButtonMenuItem {
    private boolean isHistory = false;
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
        super("buttonHistory.png", "История");
    }

    @Override
    protected void action() {

    }
}
