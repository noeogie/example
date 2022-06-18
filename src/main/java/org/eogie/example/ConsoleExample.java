package org.eogie.example;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConsoleExample {

    public static void main(String[] args) {
        new ConsoleExample().start();
    }

    public void start() {
        try (Scanner in = new Scanner(System.in);
                PrintWriter out = new PrintWriter(new FileWriter("result/result_console.txt", true), true)) {
            String line;
            while ((line = in.nextLine()) != null) {
                out.println("reply = " + line);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
