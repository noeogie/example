package org.eogie.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class FileExample {

    public static void main(String[] args) {
        new FileExample().start();
    }

    public void start() {
        try (BufferedReader in = new BufferedReader(new FileReader("data/sample.txt"));
                PrintWriter out = new PrintWriter("result/result_file.txt")) {
            String line;
            while ((line = in.readLine()) != null) {
                String num = line.split("#")[0];
                String name = line.split("#")[1];

                out.println(num + "_" + name);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
