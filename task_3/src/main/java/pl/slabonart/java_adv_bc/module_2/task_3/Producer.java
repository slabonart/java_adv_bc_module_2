package pl.slabonart.java_adv_bc.module_2.task_3;

import java.util.Random;

public class Producer implements Runnable {

    private final String name;
    private final MessageBus messageBus;
    private final String[] topics;
    private final Random random = new Random();

    public Producer(String name, MessageBus messageBus, String[] topics) {
        this.name = name;
        this.messageBus = messageBus;
        this.topics = topics;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String topic = getRandomTopic();
                String payload = "Payload-" + random.nextInt(100);
                Message message = new Message(topic, payload);

                System.out.println(name + "\t produced: " + message);
                messageBus.publish(topic, message);

                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private String getRandomTopic() {
        return topics[random.nextInt(topics.length)];
    }
}


