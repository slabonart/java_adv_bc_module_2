package pl.slabonart.java_adv_bc.module_2.task_3;

public class Main {
    public static void main(String[] args) {

        MessageBus messageBus = new MessageBus();

        String[] topics = {"topic1", "topic2", "topic3"};

        for (String topic : topics) {
            messageBus.subscribe(topic, new ConsumerTask("Consumer-" + topic));
        }

        Thread producer1 = new Thread(new Producer("Producer-1", messageBus, topics));
        Thread producer2 = new Thread(new Producer("Producer-2", messageBus, topics)) ;

        producer1.start();
        producer2.start();

        try {
            producer1.join();
            producer2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}