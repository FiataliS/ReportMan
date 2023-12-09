package com.fiatalis;

import com.fiatalis.CRUD.Frequency;
import com.fiatalis.entytis.Entity;
import com.fiatalis.entytis.Reports;
import com.fiatalis.windows.components.center.modelTable.ReportModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MonitoringUtils {
    private List<Entity> reportsList;
    private ArrayList<Color> colors;

//    static void start() {
//        new MonitoringUtils();
//    }

//    public MonitoringUtils() {

//        new Thread(() -> {
//            while (true) {
//                Table.getInstance().setColorRow();
//                try {
//                    Thread.sleep(6000); // 3600000 проверка раз в час. 60000 минута
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();
//    }

    public ArrayList<Color> managerFrequency() {
        colors = new ArrayList<>();
        reportsList = ReportModel.getInstance().getEntityListFromModel();
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
