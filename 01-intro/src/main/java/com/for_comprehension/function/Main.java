package com.for_comprehension.function;

class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Hello from another thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(false);
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
    }
}
