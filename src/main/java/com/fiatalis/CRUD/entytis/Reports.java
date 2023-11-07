package com.fiatalis.CRUD.entytis;

import com.fiatalis.CRUD.Frequency;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

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

    public void setDate(Object date) {
        if (date instanceof java.util.Date) {
            java.util.Date dateUtils = (java.util.Date) date;
            this.date = new Date(dateUtils.getTime());
        } else if (date instanceof Date) {
            this.date = (Date) date;
        } else if (date instanceof String) {
            String string = (String) date;
            if (string == null || string.equals("") || string.equals("null")) return;
            StringBuilder sb = new StringBuilder();
            sb.append("00");
            sb.append(String.copyValueOf(string.toCharArray(), string.length() - 4, 2));
            sb.append("-");
            sb.append(String.copyValueOf(string.toCharArray(), string.length() - 7, 2));
            sb.append("-");
            sb.append(String.copyValueOf(string.toCharArray(), 0, 2));
            this.date = Date.valueOf(sb.toString());
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reports reports = (Reports) o;
        return Objects.equals(id, reports.id) && Objects.equals(name, reports.name) && Objects.equals(date, reports.date) && frequency == reports.frequency && Objects.equals(submitted, reports.submitted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, frequency, submitted);
    }
}
