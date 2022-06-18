package org.eogie.example;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) {
        new FutureExample().start();
    }

    public void start() {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> future1 = compute(3, executorService);
        CompletableFuture<Integer> future2 = compute(5, executorService);
        CompletableFuture<Integer> future3 = compute(3, executorService);

        try {
            CompletableFuture.allOf(future1, future2, future3).get();
            System.out.println(future1.get() + future2.get() + future3.get());
            System.out.println("total time = " + (System.currentTimeMillis() - start));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }

    private CompletableFuture<Integer> compute(int num, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(num * 1000L);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return num;
        }, executor);
    }

}
