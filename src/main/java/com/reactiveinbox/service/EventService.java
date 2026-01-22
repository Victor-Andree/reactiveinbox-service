package com.reactiveinbox.service;

import com.reactiveinbox.model.EventDocument;
import com.reactiveinbox.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;


    public Flux<EventDocument> findAll(){
        return eventRepository.findAll();
    }

}
