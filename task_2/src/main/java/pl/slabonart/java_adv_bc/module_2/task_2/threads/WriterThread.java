package pl.slabonart.java_adv_bc.module_2.task_2.threads;

import java.util.List;

public class WriterThread implements Runnable {

    private final List<Integer> collection;

    public WriterThread(List<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public void run() {

        while(true) {
            System.out.println("Writer adds " + collection.size() + 1);
            collection.add(collection.size() + 1);
        }

    }
}
