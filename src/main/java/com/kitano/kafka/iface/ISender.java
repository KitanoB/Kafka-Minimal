package com.kitano.kafka.iface;

import com.kitano.kafka.dto.Person;

public interface ISender {
    void send(String topicName, Person person);
}