package pl.slabonart.java_adv_bc.module_2.task_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MessageBus {

    private final Map<String, List<Message>> topicMessages = new HashMap<>();
    private final Map<String, List<Consumer<Message>>> subscribers = new HashMap<>();
    private final Object lock = new Object();

    public void publish(String topic, Message message) {
        synchronized (lock) {
            topicMessages.computeIfAbsent(topic, k -> new ArrayList<>()).add(message);
            lock.notifyAll();
        }
    }

    public void subscribe(String topic, Consumer<Message> consumer) {
        synchronized (lock) {
            subscribers.computeIfAbsent(topic, k -> new ArrayList<>()).add(consumer);
        }

        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    try {
                        while (topicMessages.getOrDefault(topic, new ArrayList<>()).isEmpty()) {
                            lock.wait();
                        }

                        Message message = topicMessages.get(topic).removeFirst();
                        consumer.accept(message);

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }).start();
    }
}
