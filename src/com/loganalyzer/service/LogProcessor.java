package com.loganalyzer.service;

import java.util.List;

public class LogProcessor extends Thread {

    private List<String> logs;
    private String logType;
    private int count = 0;

    public LogProcessor(List<String> logs, String logType) {
        this.logs = logs;
        this.logType = logType.toUpperCase();
    }

    @Override
    public void run() {
        for (String log : logs) {
            if (log.toUpperCase().contains(logType)) {
                count++;
            }
        }
    }

    public int getCount() {
        return count;
    }
}