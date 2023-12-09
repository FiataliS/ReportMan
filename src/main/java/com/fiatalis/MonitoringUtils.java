package com.fiatalis;

import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.CRUD.Frequency;
import com.fiatalis.entytis.Entity;
import com.fiatalis.entytis.Executor;
import com.fiatalis.entytis.Reports;

import java.util.List;

public class MonitoringUtils {
    private List<Entity> reportsList;
    private List<Entity> executorList;


    static void start() {
        new MonitoringUtils();
    }

    public MonitoringUtils() {
        new Thread(() -> {
            while (true) {
                alertConstructor();
                try {
                    Thread.sleep(6000); // 3600000 проверка раз в час. 60000 минута
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void alertConstructor() {
        ReportsDAO reportsDAO = new ReportsDAO();
        reportsList = reportsDAO.findAll(null);
        if (reportsList.size() == 0) return;
        StringBuilder sb = new StringBuilder();
        for (Entity entity : reportsList) {
            sb.append(handlerReport((Reports) entity));
        }
        alert("", sb.toString());
    }

    private String handlerReport(Reports reports) {
        if (!reports.getSubmitted()) return "";
        return reports.getName() + "\n" + handlerExecutor(reports);
    }

    private String handlerExecutor(Reports reports) {
        ExecutorDAO executorDAO = new ExecutorDAO();
        List<Entity> listExecutor = executorDAO.findAll(reports.getId());
        if (listExecutor.size() == 0) return "Нет организаций.";
        StringBuilder sb = new StringBuilder();
        for (Entity entity : listExecutor) {
            Executor executor = (Executor) entity;
            if (!executor.getSubmit()) {
                sb.append(executor.getName() + "\n");
            }
        }
        return sb.toString();
    }

    private void inspector(Reports reports) {
        if (reports.getFrequency().equals(Frequency.Monthly)) {
            monthly(reports);
        } else if (reports.getFrequency().equals(Frequency.Quarterly)) {
            quarterly(reports);
        }
    }


    private void monthly(Reports reports) {
        alert(reports.getName(), executorList.toString());

    }

    private void quarterly(Reports reports) {
        alert(reports.getName(), executorList.toString());
    }

    private static void alert(String title, String text) {
        //System.out.println(title);
        System.out.println(text);
    }

}
