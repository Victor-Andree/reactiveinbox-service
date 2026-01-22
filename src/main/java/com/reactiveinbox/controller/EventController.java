package com.reactiveinbox.controller;


import com.reactiveinbox.dto.EventRequest;
import com.reactiveinbox.dto.EventResponse;
import com.reactiveinbox.dto.EventView;
import com.reactiveinbox.model.EventDocument;
import com.reactiveinbox.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor

public class EventController {

    private final EventService eventService;

    @GetMapping
    public Flux<EventDocument> getEvents() {
        return eventService.findAll();
    }

    @PostMapping
    public Mono<EventResponse> publishEvent(@Valid @RequestBody EventRequest request) {
        return eventService.publishEvent(request);
    }

    @GetMapping("/view")
    public Flux<EventView> getEventsView() {
        return eventService.findAllView();
    }




}
