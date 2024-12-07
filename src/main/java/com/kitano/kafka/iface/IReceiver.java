package com.kitano.kafka.iface;

import com.kitano.kafka.dto.Person;

public interface IReceiver {
    void listen(String topicName, Person person);
}
