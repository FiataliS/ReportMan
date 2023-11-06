package com.fiatalis.CRUD.entytis;

import com.fiatalis.CRUD.Frequency;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
public class Reports implements Entity {

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
        if (date == null) return "";
        return new SimpleDateFormat("dd.MM.yyг.").format(date);
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

    public void setFrequencyInString(String frequency) {
        if (frequency.equals("null") || frequency.equals(Frequency.None.getName())) {
            this.frequency = Frequency.None;
        } else if (frequency.equals(Frequency.Monthly.getName())) {
            this.frequency = Frequency.Monthly;
        } else if (frequency.equals(Frequency.Quarterly.getName())) {
            this.frequency = Frequency.Quarterly;
        }
    }
}
