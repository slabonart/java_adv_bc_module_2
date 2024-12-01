package pl.slabonart.java_adv_bc.module_2.task_1.threads;

import java.util.Map;

public class WriterTask implements Runnable {

    private final Map<Integer, Integer> map;

    public WriterTask(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
            while (true) {
                map.put(map.size() + 1, 1);
            }
    }
}
