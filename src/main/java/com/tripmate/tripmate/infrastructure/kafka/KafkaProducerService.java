package com.tripmate.tripmate.infrastructure.kafka;

import io.reactivex.rxjava3.core.Completable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;


    @Value("${topic.name:bu-notification-topic}")
    private String topicName;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(topicName, message);
        System.out.println("Mensaje enviado a kafka: " + message);
    }

    public Completable sendMessageRx(String message) {
        return Completable.fromFuture(kafkaTemplate.send(topicName, message));
    }
}
