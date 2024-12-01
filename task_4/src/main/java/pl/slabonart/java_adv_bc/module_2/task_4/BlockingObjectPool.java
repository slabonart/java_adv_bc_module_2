package pl.slabonart.java_adv_bc.module_2.task_4;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingObjectPool {

    private final Queue<Object> pool;
    private final int size;

    public BlockingObjectPool(int size) {
        this.size = size;
        this.pool = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            pool.offer(new Object());
        }
    }

    public synchronized Object get() {
        while (pool.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while waiting for an object", e);
            }
        }
        Object obj = pool.poll();
        notifyAll();
        return obj;
    }

    public synchronized void take(Object object) {
        while (pool.size() == size) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while waiting to put an object", e);
            }
        }
        pool.offer(object);
        notifyAll();
    }


}
