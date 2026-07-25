package fr.alb.backend.service;

import org.springframework.stereotype.Service;
import fr.alb.backend.mapper.EventMapper;
import fr.alb.backend.model.entity.Event;
import fr.alb.backend.repository.EventRepository;
import fr.alb.backend.dto.request.CreateEventRequest;
import fr.alb.backend.dto.request.UpdateEventRequest;
import fr.alb.backend.dto.response.EventResponse;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventResponse> getAll() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toResponse)
                .toList();
    }

    public EventResponse getById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("L'évènement n'a pas été trouvé"));
        return eventMapper.toResponse(event);
    }

    public EventResponse getByTitle(String title) {
        Event event = eventRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("L'évènement n'a pas été trouvé"));
        return eventMapper.toResponse(event);
    }

    public EventResponse create(CreateEventRequest request) {
        Event event = eventMapper.toEntity(request);
        Event saved = eventRepository.save(event);
        return eventMapper.toResponse(saved);
    }

    public EventResponse update(Long id, UpdateEventRequest request) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("L'évènement n'a pas été trouve"));
        eventMapper.updateEntity(request, event);
        Event saved = eventRepository.save(event);
        return eventMapper.toResponse(saved);
    }

    public void delete(Long id) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("L'évènement n'a pas été trouvé"));
        eventRepository.delete(event);
    } 

}
