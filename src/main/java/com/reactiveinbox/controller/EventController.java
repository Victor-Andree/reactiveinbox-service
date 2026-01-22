package com.reactiveinbox.controller;


import com.reactiveinbox.model.EventDocument;
import com.reactiveinbox.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor

public class EventController {

    private final EventService eventService;

    @GetMapping
    public Flux<EventDocument> getEvents() {
        return eventService.findAll();
    }

}
