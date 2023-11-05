package com.fiatalis.CRUD;

import lombok.Getter;

@Getter
public enum Frequency {
    Monthly("Ежемесячно"),
    Quarterly("Ежеквартально"),
    None("Нет");

    private final String name;

    Frequency(String url) {
        this.name = url;
    }
}
