package com.fiatalis.CRUD.entytis;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Executor implements Entity{
    private long id;
    private String name;
    private String Responsible;
    private String phone;

    @Override
    public String toString() {
        return "Executor{" +
                "id=" + id +
                ", organization='" + name + '\'' +
                ", Responsible='" + Responsible + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}