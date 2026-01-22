package com.reactiveinbox.service;

import com.reactiveinbox.dto.EventRequest;
import com.reactiveinbox.dto.EventResponse;
import com.reactiveinbox.dto.EventView;
import com.reactiveinbox.kafka.producer.EventProducer;
import com.reactiveinbox.model.EventDocument;
import com.reactiveinbox.model.EventStatus;
import com.reactiveinbox.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventProducer eventProducer;



    public Flux<EventDocument> findAll(){
        return eventRepository.findAll();
    }


    public Mono<EventResponse> publishEvent(EventRequest request) {

        EventDocument event = EventDocument.builder()
                .eventId(UUID.randomUUID().toString())
                .type(request.getType())
                .source(request.getSource())
                .payload(request.getPayload())
                .status(EventStatus.PUBLISHED)
                .createdAt(Instant.now())
                .build();

        return eventProducer.publish(event)
                .thenReturn(new EventResponse(event.getEventId(), event.getStatus()));
    }


    public Flux<EventView> findAllView() {
        return eventRepository.findAll()
                .map(event -> new EventView(
                        event.getEventId(),
                        event.getType(),
                        event.getSource(),
                        event.getPayload(),
                        event.getStatus(),
                        event.getCreatedAt(),
                        event.getProcessedAt()
                ));
    }


}
