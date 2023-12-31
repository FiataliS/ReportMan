package com.fiatalis.utils;

import com.fiatalis.CRUD.Frequency;
import com.fiatalis.entytis.Entity;
import com.fiatalis.entytis.Report;

import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;

public class MonitoringUtils {
    private ArrayList<Color> colors;
    private Report report;

    public ArrayList<Color> managerColors(ArrayList<Entity> reportsList) {
        colors = new ArrayList<>();
        for (int i = 0; i < reportsList.size(); i++) colors.add(Color.WHITE);
        if (reportsList.size() == 0) return colors;
        for (int i = 0; i < reportsList.size(); i++) {
            report = (Report) reportsList.get(i);
            if (report.getSubmitted()) managerFrequency(report, i);
        }
        return colors;
    }

    private void managerFrequency(Report report, int indexRow) {
        if (report.getFrequency().equals(Frequency.Monthly)) {
            monthly(indexRow);
        } else if (report.getFrequency().equals(Frequency.Quarterly)) {
            quarterly(indexRow);
        } else if (report.getFrequency().equals(Frequency.Weekly)) {
            weekly(indexRow);
        }
    }

    private void monthly(int indexRow) {
        colors.set(indexRow, gradient());
    }

    private void quarterly(int indexRow) {
        colors.set(indexRow, gradient());
    }

    private void weekly(int indexRow) {
        colors.set(indexRow, gradient());
    }

    private Color gradient() {
        if (report.getDate() == null) return Color.WHITE;
        LocalDate startDate = LocalDate.now();
        Calendar cal = Calendar.getInstance();
        cal.setTime(report.getDate());
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR) + 2000;
        LocalDate endDate = LocalDate.of(year, month, day);
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        Color color = Color.white;
        if (5 >= days && days >= 2) {
            color = Color.YELLOW;
        }
        if (1 >= days) {
            color = Color.RED;
        }
        return color;
    }
}
