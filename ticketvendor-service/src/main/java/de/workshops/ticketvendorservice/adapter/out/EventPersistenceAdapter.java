package de.workshops.ticketvendorservice.adapter.out;

import de.workshops.ticketvendorapi.model.Event;
import de.workshops.ticketvendorapi.model.EventCategory;
import de.workshops.ticketvendorapi.model.EventStatus;
import de.workshops.ticketvendorservice.application.EventLoadPort;
import de.workshops.ticketvendorservice.application.EventUpdatePort;
import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
class EventPersistenceAdapter implements EventLoadPort, EventUpdatePort {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    EventPersistenceAdapter(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public List<Event> getAllEvents() {
        var all = eventRepository.findAll();
        return eventMapper.mapToModel(all);
    }

    @Override
    public @NonNull Event getEvent(@NonNull UUID uuid) {
        var eventEntity = eventRepository.findByEventId(uuid).orElseThrow(EntityNotFoundException::new);
        return eventMapper.mapToModel(eventEntity);
    }

    @Override
    public @NonNull List<Event> getEventsByCategory(@NonNull EventCategory eventCategory) {
        var byCategory = eventRepository.findByCategory(eventCategory);
        return eventMapper.mapToModel(byCategory);
    }

    @Override
    public @NonNull List<Event> getEventsByCity(@NonNull String city) {
        var byCity = eventRepository.findByCity(city);
        return eventMapper.mapToModel(byCity);
    }

    @Override
    public void setEventStatus(UUID eventId, EventStatus eventStatus) {
        eventRepository.updateEventStatus(eventId, eventStatus);
    }
}
