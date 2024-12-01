package pl.slabonart.java_adv_bc.module_2.task_2.threads;

import java.util.List;

public class SumReaderThread implements Runnable {

    private final List<Integer> collection;

    public SumReaderThread(List<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (collection) {
                System.out.println("Sum: " + collection.stream()
                        .mapToInt(Integer::intValue)
                        .sum());
            }
        }
    }
}
