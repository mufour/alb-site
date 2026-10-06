package fr.alb.backend.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import fr.alb.backend.dto.request.CreateEventRequest;
import fr.alb.backend.dto.request.UpdateEventRequest;
import fr.alb.backend.dto.response.EventResponse;
import fr.alb.backend.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventResponse> getAll() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public EventResponse getById(@PathVariable Long id) {
        return eventService.getById(id);
    }

    @GetMapping("/title/{title}")
    public EventResponse getByTitle(@PathVariable String title) {
        return eventService.getByTitle(title);
    }

    @PostMapping
    public EventResponse create(@Valid @RequestBody CreateEventRequest request) {
        return eventService.create(request);
    }

    @PutMapping("/{id}")
    public EventResponse update(@PathVariable Long id, @Valid @RequestBody UpdateEventRequest request) {
        return eventService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventService.delete(id);
    }
}