package com.urise.webapp;

public class DeadLock {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(deadLock(lock1, lock2));
        Thread thread2 = new Thread(deadLock(lock2, lock1));
        thread1.start();
        thread2.start();
    }

    private static Thread deadLock(Object lock1, Object lock2) {
        return new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (lock1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }
            System.out.println(Thread.currentThread().getName() + " end");
        });
    }
}
