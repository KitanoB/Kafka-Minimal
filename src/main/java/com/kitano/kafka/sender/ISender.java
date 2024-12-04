package com.kitano.kafka.sender;

import com.kitano.kafka.dto.Person;

public interface ISender {
    void send(String topicName, Person person);
}