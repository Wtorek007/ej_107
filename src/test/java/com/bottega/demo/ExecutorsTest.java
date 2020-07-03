package com.bottega.demo;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorsTest {

    @Test
    public void example_1() throws Exception {
        // newFixedThreadPool od newCachedThreadPool

        ExecutorService fixedThreadCount = Executors.newFixedThreadPool(10);
        ExecutorService elasticThreadCount = Executors.newCachedThreadPool();
        ExecutorService scheduled = new ScheduledThreadPoolExecutor(1);

        ExecutorService inlined = new ThreadPoolExecutor(10, 100,
          60L, TimeUnit.SECONDS,
          new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
