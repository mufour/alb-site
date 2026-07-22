package fr.alb.backend.mapper;

import org.springframework.stereotype.Component;

import fr.alb.backend.dto.request.CreateEventRequest;
import fr.alb.backend.dto.request.UpdateEventRequest;
import fr.alb.backend.dto.response.EventResponse;
import fr.alb.backend.model.entity.Event;

@Component
public class EventMapper {

    public Event toEntity(CreateEventRequest request) {

        Event event = new Event();

        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setStartDate(request.getStartDate());
        event.setEndDate(request.getEndDate());
        event.setLocation(request.getLocation());
        event.setPublished(request.getPublished());

        return event;
    }

    public EventResponse toResponse(Event event) {

        EventResponse response = new EventResponse();

        response.setId(event.getId());
        response.setTitle(event.getTitle());
        response.setDescription(event.getDescription());
        response.setStartDate(event.getStartDate());
        response.setEndDate(event.getEndDate());
        response.setLocation(event.getLocation());
        response.setPublished(event.getPublished());

        if (event.getImage() != null) {
            response.setImageId(event.getImage().getId());
        }

        return response;
    }

    public void updateEntity(UpdateEventRequest request, Event event) {

        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setStartDate(request.getStartDate());
        event.setEndDate(request.getEndDate());
        event.setLocation(request.getLocation());
        event.setPublished(request.getPublished());
    }

}
