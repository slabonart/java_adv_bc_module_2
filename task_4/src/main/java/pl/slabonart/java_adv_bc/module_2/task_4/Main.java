package pl.slabonart.java_adv_bc.module_2.task_4;

public class Main {
    public static void main(String[] args) {

        BlockingObjectPool pool = new BlockingObjectPool(3);

        Thread thread1 = new Thread(new GetterThread(pool), "Thread-1");
        Thread thread2 = new Thread(new GetterThread(pool), "Thread-2");

        thread1.start();
        thread2.start();
    }
}