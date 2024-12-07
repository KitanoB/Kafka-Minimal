package com.kitano.kafka.service;

import com.kitano.kafka.dto.Person;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class KafkaListenerService {

    private final List<SseEmitter> emitters = Collections.synchronizedList(new ArrayList<>());
    private final List<String> receivedMessages = Collections.synchronizedList(new ArrayList<>());

    public List<String> getReceivedMessages() {
        return Collections.unmodifiableList(receivedMessages);
    }

    public List<SseEmitter> getEmitters() {
        return emitters;
    }

    @KafkaListener(topics = {"Tuto1", "Tuto2"}, containerFactory = "kafkaListenerContainerFactory")
    public void listenKafkaMessages(Person person) {
        String message = "Received: " + person;
        receivedMessages.add(message);
        System.out.println("Current messages: " + receivedMessages);

        synchronized (emitters) {
            Iterator<SseEmitter> iterator = emitters.iterator();
            while (iterator.hasNext()) {
                SseEmitter emitter = iterator.next();
                try {
                    emitter.send(SseEmitter.event().data(message));
                    System.out.println("Message sent to SSE client: " + message);
                } catch (IOException e) {
                    System.err.println("Failed to send message via SSE: " + e.getMessage());
                    iterator.remove();
                    System.out.println("Removed disconnected SSE emitter. Total emitters: " + emitters.size());
                }
            }
        }
    }
}
