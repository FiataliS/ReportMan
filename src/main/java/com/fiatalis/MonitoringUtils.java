package com.fiatalis;

import com.fiatalis.CRUD.Frequency;
import com.fiatalis.entytis.Entity;
import com.fiatalis.entytis.Reports;

import java.awt.*;
import java.util.ArrayList;

public class MonitoringUtils {
    private ArrayList<Color> colors;

    public ArrayList<Color> managerColors(ArrayList<Entity> reportsList) {
        colors = new ArrayList<>();
        for (int i = 0; i < reportsList.size(); i++) colors.add(Color.WHITE);
        if (reportsList.size() == 0) return colors;
        for (int i = 0; i < reportsList.size(); i++) {
            Reports report = (Reports) reportsList.get(i);
            if (report.getSubmitted()) managerFrequency(report, i);
        }
        return colors;
    }

    private void managerFrequency(Reports reports, int indexRow) {
        if (reports.getFrequency().equals(Frequency.Monthly)) {
            monthly(indexRow);
        } else if (reports.getFrequency().equals(Frequency.Quarterly)) {
            quarterly(indexRow);
        }
    }

    private void monthly(int indexRow) {
        colors.set(indexRow, Color.green);
    }

    private void quarterly(int indexRow) {
        colors.set(indexRow, Color.RED);
    }
}
