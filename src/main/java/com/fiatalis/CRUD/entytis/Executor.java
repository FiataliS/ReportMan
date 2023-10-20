package com.fiatalis.CRUD.entytis;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Executor implements Entity {

    private Long id = -1L;
    private Long idReport;
    private String name;
    private String Responsible;
    private String phone;

    @Override
    public String toString() {
        return "Executor{" +
                "id=" + id +
                ", idReport=" + idReport +
                ", name='" + name + '\'' +
                ", Responsible='" + Responsible + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
