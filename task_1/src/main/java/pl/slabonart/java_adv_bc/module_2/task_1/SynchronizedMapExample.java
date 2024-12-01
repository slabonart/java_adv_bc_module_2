package pl.slabonart.java_adv_bc.module_2.task_1;

import pl.slabonart.java_adv_bc.module_2.task_1.threads.SynchronizedMapReaderTask;
import pl.slabonart.java_adv_bc.module_2.task_1.threads.WriterTask;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedMapExample {

    public static void main(String[] args) {
        Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());

        Thread writerThread = new Thread(new WriterTask(map));
        Thread readerThread = new Thread(new SynchronizedMapReaderTask(map));

        writerThread.start();
        readerThread.start();

        try {
            readerThread.join();
        } catch (InterruptedException | RuntimeException e) {
            System.out.println("Main thread interrupted.");
        } finally {
            writerThread.interrupt();
            System.exit(1);
        }
    }
}
