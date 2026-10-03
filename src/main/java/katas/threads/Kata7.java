package katas.threads;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Kata7 {
    // Pretend this is a slow database or API call: waits 100 ms, uses no CPU.
    static String fetchPrice(int i) {
        sleep100();
        return "price-" + i;
    }

    static void sleep100() {
        try {
            Thread.sleep(Duration.ofMillis(100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void run(String label, ExecutorService executor,int task){
        long start=System.nanoTime();
        try(executor){
            IntStream.range(0,task).forEach(i->executor.submit(()->fetchPrice(i)));
        }
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.println(label + ": " + task + " tasks took " + ms + " ms");
    }

    public static void main(String[] args) {
        run("Fixed pool, 200 threads", Executors.newFixedThreadPool(200), 10_000);
        run("Virtual threads        ", Executors.newVirtualThreadPerTaskExecutor(), 10_000);
    }
}
