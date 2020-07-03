package com.bottega.demo;

class InterruptedException {

    public static void main(String[] args) throws java.lang.InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Hello from another thread");
                try {
                    Thread.sleep(1000);
                } catch (java.lang.InterruptedException e) {
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
