package com.kitano.kafka.receiver;

import com.kitano.kafka.dto.Person;
import org.springframework.stereotype.Service;

@Service
public class ProcessExample implements IProcess {

    /**
     * Execute the process : display the message received in the console
     * @param info
     * @param person
     */
    @Override
    public void execute(String info, Person person) {
        System.out.println("Message Received: info="+info +", person=" + person);
    }
}