package pl.slabonart.java_adv_bc.module_2.task_1;

import pl.slabonart.java_adv_bc.module_2.task_1.threads.ReaderTask;
import pl.slabonart.java_adv_bc.module_2.task_1.threads.WriterTask;

public class CustomThreadSafeMapExample {

    public static void main(String[] args) {
        ThreadSafeMap map = new ThreadSafeMap();

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
