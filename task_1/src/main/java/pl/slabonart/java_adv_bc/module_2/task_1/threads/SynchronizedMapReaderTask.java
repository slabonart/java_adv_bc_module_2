package pl.slabonart.java_adv_bc.module_2.task_1.threads;

import java.util.ConcurrentModificationException;
import java.util.Map;

public class SynchronizedMapReaderTask extends ReaderTask {

    public SynchronizedMapReaderTask(Map<Integer, Integer> map) {
        super(map);
    }

    @Override
    public void run() {

        long start = System.nanoTime();

        try {
            while (true) {
                synchronized (map) {
                    System.out.println("Sum: " + map.values().stream()
                            .mapToInt(Integer::intValue)
                            .sum());
                    if (map.size() > 1_000_000) {
                        System.out.println("Elapsed time: " + (System.nanoTime() - start) / 1_000_000 + " ms");
                        break;
                    }
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Reader caught ConcurrentModificationException! (map size = " + map.size() + ")");
            throw new RuntimeException();
        }
    }
}
