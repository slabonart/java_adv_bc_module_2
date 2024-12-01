package pl.slabonart.java_adv_bc.module_2.task_1;



import pl.slabonart.java_adv_bc.module_2.task_1.threads.ReaderTask;
import pl.slabonart.java_adv_bc.module_2.task_1.threads.WriterTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();

        Thread writerThread = new Thread(new WriterTask(map));
        Thread readerThread = new Thread(new ReaderTask(map));

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
