package pl.slabonart.java_adv_bc.module_2.task_2;

import pl.slabonart.java_adv_bc.module_2.task_2.threads.SquareReaderThread;
import pl.slabonart.java_adv_bc.module_2.task_2.threads.SumReaderThread;
import pl.slabonart.java_adv_bc.module_2.task_2.threads.WriterThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> collection = Collections.synchronizedList(new ArrayList<>());

        Thread writer = new Thread(new WriterThread(collection));
        Thread sumReader = new Thread(new SumReaderThread(collection));
        Thread squareReader = new Thread(new SquareReaderThread(collection));

        writer.start();
        sumReader.start();
        squareReader.start();

    }
}