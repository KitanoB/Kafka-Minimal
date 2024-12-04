package com.kitano.kafka.receiver;

import com.kitano.kafka.dto.Person;

interface IReceiver {
    void listen(String topicName, Person person);
}
