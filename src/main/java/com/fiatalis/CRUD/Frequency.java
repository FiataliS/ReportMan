package com.fiatalis.CRUD;

import lombok.Getter;

@Getter
public enum Frequency {
    Weekly("Еженедельно"),
    Monthly("Ежемесячно"),
    Quarterly("Ежеквартально");

    private final String name;

    Frequency(String frequency) {
        this.name = frequency;
    }

    public static Frequency getFrequency(String string) {
        Frequency frequency = null;
        switch (string) {
            case "Еженедельно":
                frequency = Weekly;
                break;
            case "Ежемесячно":
                frequency = Monthly;
                break;
            case "Ежеквартально":
                frequency = Quarterly;
                break;
        }
        return frequency;
    }
}
