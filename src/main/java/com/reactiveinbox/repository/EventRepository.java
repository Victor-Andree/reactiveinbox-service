package com.reactiveinbox.repository;

import com.reactiveinbox.model.EventDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EventRepository extends ReactiveMongoRepository<EventDocument, String> {
}
