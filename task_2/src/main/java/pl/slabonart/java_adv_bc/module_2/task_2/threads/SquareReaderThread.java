package pl.slabonart.java_adv_bc.module_2.task_2.threads;

import java.util.List;

public class SquareReaderThread implements Runnable {

    private final List<Integer> collection;

    public SquareReaderThread(List<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (collection) {
                System.out.println("Square: " + Math.sqrt(collection.stream()
                        .mapToInt(Integer::intValue)
                        .map(i -> i * i)
                        .sum()));
            }
        }
    }
}
