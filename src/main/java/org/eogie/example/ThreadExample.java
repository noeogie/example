package org.eogie.example;

public class ThreadExample {

    public static void main(String[] args) {
        new ThreadExample().start();
    }

    public void start() {
        long start = System.currentTimeMillis();

        Thread t1;
        Thread t2;
        Thread t3;
        Task task1 = new Task(3);
        Task task2 = new Task(6);
        Task task3 = new Task(1);

        (t1 = new Thread(task1)).start();
        (t2 = new Thread(task2)).start();
        (t3 = new Thread(task3)).start();

        try {
            t1.join();
            t2.join();
            t3.join();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(task1.num + task2.num + task3.num);
        System.out.println("total time = " + (System.currentTimeMillis() - start));
    }

    static class Task implements Runnable {
        public int num;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(num * 1000L);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
