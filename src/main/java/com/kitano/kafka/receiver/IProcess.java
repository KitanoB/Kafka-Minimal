package com.kitano.kafka.receiver;

import com.kitano.kafka.dto.Person;

public interface IProcess {
    void execute(String info, Person person);
}
