package com.loganalyzer.app;

import com.loganalyzer.service.LogProcessor;

import java.io.*;
import java.util.*;

public class LogAnalyzerApp {

    public static void main(String[] args) {

        List<String> logs = new ArrayList<>();

        
        try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                logs.add(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
            return;
        }

        
        LogProcessor infoThread = new LogProcessor(logs, "INFO");
        LogProcessor errorThread = new LogProcessor(logs, "ERROR");
        LogProcessor warningThread = new LogProcessor(logs, "WARNING");

        
        infoThread.start();
        errorThread.start();
        warningThread.start();

       
        try {
            infoThread.join();
            errorThread.join();
            warningThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        
        System.out.println("INFO Count: " + infoThread.getCount());
        System.out.println("ERROR Count: " + errorThread.getCount());
        System.out.println("WARNING Count: " + warningThread.getCount());
    }
}