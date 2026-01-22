package com.reactiveinbox.kafka.consumer;

import com.reactiveinbox.model.EventDocument;
import com.reactiveinbox.model.EventStatus;
import com.reactiveinbox.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventConsumer {

    private final EventRepository eventRepository;

    @KafkaListener(
            topics = "events.inbox",
            groupId = "reactive-inbox-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(EventDocument event) {

        log.info("Event received from Kafka: {}", event.getEventId());

        EventDocument processedEvent = EventDocument.builder()
                .id(event.getId())
                .eventId(event.getEventId())
                .type(event.getType())
                .source(event.getSource())
                .payload(event.getPayload())
                .status(EventStatus.PROCESSED)
                .createdAt(event.getCreatedAt())
                .processedAt(Instant.now())
                .build();

        eventRepository.save(processedEvent)
                .doOnSuccess(saved ->
                        log.info("Event saved in MongoDB: {}", saved.getEventId())
                )
                .doOnError(error ->
                        log.error("Error saving event {}", event.getEventId(), error)
                )
                .subscribe();
    }


}
