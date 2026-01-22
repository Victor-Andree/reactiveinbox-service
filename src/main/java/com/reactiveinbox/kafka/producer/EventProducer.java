package com.reactiveinbox.kafka.producer;


import com.reactiveinbox.kafka.config.KafkaTopicsConfig;
import com.reactiveinbox.model.EventDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EventProducer {

    private final KafkaTemplate<String, EventDocument> kafkaTemplate;

    public Mono<Void> publish(EventDocument eventDocument) {
        return Mono.fromFuture(
                kafkaTemplate.send(KafkaTopicsConfig.EVENTS_INBOX_TOPIC, eventDocument.getEventId(), eventDocument).toCompletableFuture())
                .then();

    }

}
