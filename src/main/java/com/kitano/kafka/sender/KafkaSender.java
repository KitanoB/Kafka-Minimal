package com.kitano.kafka.sender;

import com.kitano.kafka.dto.Person;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender implements ISender {


    @Autowired
    private KafkaTemplate<String, Person> kafkaTemplate;

    /**
     * Allows to send a message to a topic. If the topic does not exist, it will be created
     * @param topicName
     * @param person
     */
    @Override
    public void send(String topicName, Person person) {
        System.out.println("Message To Send : topicName="+topicName +", person=" + person);
        new NewTopic(topicName, 1, (short) 1);
        kafkaTemplate.send(topicName, person);
    }
}