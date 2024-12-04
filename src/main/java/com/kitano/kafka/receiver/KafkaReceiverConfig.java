package com.kitano.kafka.receiver;

import com.kitano.kafka.dto.Person;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaReceiverConfig {

    @Value(value = "${kafka.bootstrapAddress:kafka:9092}") // Kafka server address (it is possible to configure it via application.properties)
    private String bootstrapAddress;

    private static final String groupId = "Tutorial"; // Group ID : Tutorial

    @Bean
    public ConsumerFactory<String, Person> receiverFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress); // Server address configuration
        props.put(ConsumerConfig.GROUP_ID_CONFIG,groupId); // Group ID configuration
        return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(),new JsonDeserializer<>(Person.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Person> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Person> factory =new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(receiverFactory());
        return factory;
    }
}
