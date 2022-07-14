package org.eogie.example;

import java.util.ArrayList;
import java.util.List;

public class MultiProcess {

    public static void main(String[] args) {
        new MultiProcess().start();
    }

    public void start() {
        Process process1 = makeProcess("1");
        Process process2 = makeProcess("2");

        try {
            process1.waitFor();
            process2.waitFor();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Process makeProcess(String processNo) {
        try {
            List<String> params = new ArrayList<>();
            params.add(processNo);
            params.add("param1");
            params.add("param2");
            params.add("param3");

            List<String> command = new ArrayList<>();
            command.add("java");
            command.add("-cp");
            command.add(System.getProperty("java.class.path"));
            command.add("org.eogie.example.ChildProcess");
            command.addAll(params);

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO();

            return processBuilder.start();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
