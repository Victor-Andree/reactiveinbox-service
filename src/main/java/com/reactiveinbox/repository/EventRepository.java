package com.reactiveinbox.repository;

import com.reactiveinbox.model.EventDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface EventRepository extends ReactiveMongoRepository<EventDocument, String> {

    Mono<EventDocument> findByEventId(String eventId);


}
