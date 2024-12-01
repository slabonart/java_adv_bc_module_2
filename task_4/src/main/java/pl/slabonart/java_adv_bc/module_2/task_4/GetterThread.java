package pl.slabonart.java_adv_bc.module_2.task_4;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetterThread implements Runnable {

    private final BlockingObjectPool pool;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Object object = getObjectFromPool();
            mockProcessingObject();
            takeObject(object);
        }
    }

    private Object getObjectFromPool() {
        Object object = pool.get();
        System.out.println(Thread.currentThread().getName() + " got object: " + object);
        return object;
    }

    private void mockProcessingObject() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void takeObject(Object object) {
        pool.take(object);
        System.out.println(Thread.currentThread().getName() + " returned object: " + object);
    }
}
