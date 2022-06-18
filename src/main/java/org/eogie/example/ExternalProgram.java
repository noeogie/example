package org.eogie.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ExternalProgram {
    public static void main(String[] args) {
        new ExternalProgram().start();
    }

    public void start() {
        ProcessBuilder processBuilder = new ProcessBuilder("data/example.bat");
        try {
            Process process = processBuilder.start();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    PrintWriter out = new PrintWriter(process.getOutputStream(), true)) {
                out.println("1234567890");
                String line = in.readLine();
                System.out.println("line1 = " + line);
                out.println("ABCDEFG");
                line = in.readLine();
                System.out.println("line2 = " + line);
                out.println("abcdefg");
                line = in.readLine();
                System.out.println("line3 = " + line);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
