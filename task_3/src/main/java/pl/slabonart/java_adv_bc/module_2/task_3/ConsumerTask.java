package pl.slabonart.java_adv_bc.module_2.task_3;

import java.util.function.Consumer;

public class ConsumerTask implements Consumer<Message> {

    private final String consumerName;

    public ConsumerTask(String consumerName) {
        this.consumerName = consumerName;
    }

    @Override
    public void accept(Message message) {
        System.out.println(consumerName + " consumed: " + message);
    }
}
