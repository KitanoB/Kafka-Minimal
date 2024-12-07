package com.kitano.kafka.receiver;

import com.kitano.kafka.dto.Person;
import com.kitano.kafka.iface.IReceiver;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiverImpl implements IReceiver {

    @KafkaListener(topics = "Tuto1", containerFactory = "kafkaListenerContainerFactory")
    public void listenTuto1(Person person) {
        listen("Tuto1", person);
    }

    /**
     * Listener for Tuto2 topic
     *
     * @param person Person object
     * @return void
     */
    @KafkaListener(topics = "Tuto2", containerFactory = "kafkaListenerContainerFactory")
    public void listenTuto2(Person person) {
        listen("Tuto2", person);
    }

    @Override
    public void listen(String topicName, Person person) {
        System.out.println("Received Message : topicName=" + topicName + ", person=" + person);
    }
}