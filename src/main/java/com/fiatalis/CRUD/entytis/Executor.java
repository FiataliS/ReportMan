package com.fiatalis.CRUD.entytis;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Executor executor = (Executor) o;
        return Objects.equals(id, executor.id) && Objects.equals(idReport, executor.idReport) && Objects.equals(name, executor.name) && Objects.equals(Responsible, executor.Responsible) && Objects.equals(phone, executor.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idReport, name, Responsible, phone);
    }
}
