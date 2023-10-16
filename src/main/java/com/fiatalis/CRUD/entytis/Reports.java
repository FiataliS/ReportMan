package com.fiatalis.CRUD.entytis;

import com.fiatalis.CRUD.Frequency;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Calendar;

@Data
@NoArgsConstructor
public class Reports {

    private Long id = -1L;

    private String name;

    private Date date;

    private Frequency frequency;

    private Boolean submitted;

    public Reports(String name, Date date, Frequency frequency, Boolean submitted) {
        this.name = name;
        this.date = date;
        this.frequency = frequency;
        this.submitted = submitted;
    }

    public String getDateString() {
        StringBuilder sb = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        sb.append(calendar.get(Calendar.DAY_OF_MONTH) + ".");
        sb.append(calendar.get(Calendar.MONTH) + ".");
        sb.append(calendar.get(Calendar.YEAR));
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Reports{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", frequency='" + frequency + '\'' +
                ", submitted=" + submitted +
                '}';
    }
}
