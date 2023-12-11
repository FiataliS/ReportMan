package com.fiatalis.entytis;

import com.fiatalis.CRUD.Frequency;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Report implements Entity {

    private Long id = -1L;
    private String name;
    private Date date;
    private Frequency frequency;
    private Boolean submitted;
    private String link;
    private Boolean history;

    public Report(String name, Date date, Frequency frequency, Boolean submitted) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(name, report.name) && Objects.equals(date, report.date) && frequency == report.frequency && Objects.equals(submitted, report.submitted) && Objects.equals(link, report.link) && Objects.equals(history, report.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, frequency, submitted, link, history);
    }

    @Override
    public String toString() {
        return "Reports{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", frequency=" + frequency +
                ", submitted=" + submitted +
                ", link='" + link + '\'' +
                ", history=" + history +
                '}';
    }

    public void setFrequencyFromString(String frequency) {
        this.frequency = Frequency.getFrequency(frequency);
    }
}
