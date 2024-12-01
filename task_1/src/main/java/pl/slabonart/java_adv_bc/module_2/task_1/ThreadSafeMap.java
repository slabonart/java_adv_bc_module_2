package pl.slabonart.java_adv_bc.module_2.task_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ThreadSafeMap extends HashMap<Integer, Integer> {

    private final Object lock = new Object();

    @Override
    public Integer put(Integer key, Integer value) {
        synchronized (lock) {
            return super.put(key, value);
        }
    }

    @Override
    public Collection<Integer> values() {
        synchronized (lock) {
            return new ArrayList<>(super.values());
        }
    }

    @Override
    public int size() {
        synchronized (lock) {
            return super.size();
        }
    }
}
