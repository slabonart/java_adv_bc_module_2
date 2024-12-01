package pl.slabonart.java_adv_bc.module_2.task_1;

import pl.slabonart.java_adv_bc.module_2.task_1.threads.WriterTask;
import pl.slabonart.java_adv_bc.module_2.task_1.threads.ReaderTask;

import java.util.HashMap;
import java.util.Map;


public class HashMapExample {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();

        Thread writerThread = new Thread(new WriterTask(map));
        Thread readerThread = new Thread(new ReaderTask(map));

        writerThread.start();
        readerThread.start();

        try {
            readerThread.join();
        } catch (InterruptedException e) {
            //
        } finally {
            writerThread.interrupt();
            System.exit(1);
        }

    }

}
