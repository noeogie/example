package org.eogie.example;

import java.util.Arrays;

public class ChildProcess {
    public static void main(String[] args) {
        System.out.println("Child Process start");
        System.out.println("params = " + Arrays.toString(args));

        try {
            Thread.sleep(5 * 1000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Child Process end");
    }
}
