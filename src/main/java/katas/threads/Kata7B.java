package katas.threads;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Kata7B {
    static void sleep100() {
        try {
            Thread.sleep(Duration.ofMillis(100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Waits INSIDE synchronized -> pins the carrier thread (Java 21)
    static long timePinned(int tasks) {
        var locks = IntStream.range(0, tasks).mapToObj(i -> new Object()).toList();
        long start = System.nanoTime();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (Object lock : locks) {
                executor.submit(()->{
                    synchronized (lock){
                        sleep100();
                    }
                });

            }
        }
        return (System.nanoTime() - start)/ 1_000_000;
    }

    // Waits INSIDE a ReentrantLock -> does NOT pin
    static long timeNotPinned(int tasks) {
        var locks=IntStream.range(0,tasks).mapToObj(i->new ReentrantLock()).toList();
        long start = System.nanoTime();
        try(var executor=Executors.newVirtualThreadPerTaskExecutor()){
           for(ReentrantLock lock:locks){
               executor.submit(()->{
                   lock.lock();
                   try{
                       sleep100();
                   }finally {
                       lock.unlock();
                   }
               });
           }
        }
        return (System.nanoTime()-start)/ 1_000_000;
    }
    public static void main(String[] args) {
        System.out.println("CPU cores: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Not pinned (ReentrantLock): " + timeNotPinned(200) + " ms");
        System.out.println("Pinned (synchronized):      " + timePinned(200) + " ms");
    }
}
