package com.kitano.kafka.ui;

import com.kitano.kafka.service.KafkaListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class KafkaSSEController {

    @Autowired
    private KafkaListenerService kafkaListenerService;

    @GetMapping("/stream")
    public SseEmitter stream() {
        // Timeout (3600 000 ms)
        SseEmitter emitter = new SseEmitter(60 * 60 * 1000L);
        kafkaListenerService.getEmitters().add(emitter);

        emitter.onCompletion(() -> {
            kafkaListenerService.getEmitters().remove(emitter);
            System.out.println("SSE emitter completed. Total emitters: " + kafkaListenerService.getEmitters().size());
        });

        emitter.onTimeout(() -> {
            kafkaListenerService.getEmitters().remove(emitter);
            System.out.println("SSE emitter timed out. Total emitters: " + kafkaListenerService.getEmitters().size());
        });

        System.out.println("New SSE emitter added. Total emitters: " + kafkaListenerService.getEmitters().size());
        return emitter;
    }
}
