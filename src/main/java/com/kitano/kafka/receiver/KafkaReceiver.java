package com.kitano.kafka.receiver;

import com.kitano.kafka.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver implements IReceiver {

    @Autowired
    private IProcess process; // Instance for processing


    @KafkaListener(topics = "Tuto1",containerFactory="kafkaListenerContainerFactory")
    public void listenTuto1(Person person) {
        listen("Tuto1",person);
    }

   /**
    Listener for Tuto2 topic
    *
    * @param person Person object
    * @return void
    */
    @KafkaListener(topics = "Tuto2",containerFactory="kafkaListenerContainerFactory")
    public void listenTuto2(Person person) {
        listen("Tuto2",person);
    }

   /**
    * Listener for Tuto3 topic
    *
    * @param person Person object
    * @return void
    */
    @Override
    public void listen(String topicName, Person person) {
        process.execute(topicName+"Info", person);
    }
}