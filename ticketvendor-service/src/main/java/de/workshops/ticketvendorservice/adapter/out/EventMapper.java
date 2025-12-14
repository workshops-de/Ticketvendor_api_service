package de.workshops.ticketvendorservice.adapter.out;

import de.workshops.ticketvendorapi.model.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class EventMapper {
    private final ArtistMapper artistMapper;
    private final LocationMapper locationMapper;
    private final TicketMapper ticketMapper;

    EventMapper(ArtistMapper artistMapper, LocationMapper locationMapper, TicketMapper ticketMapper) {
        this.artistMapper = artistMapper;
        this.locationMapper = locationMapper;
        this.ticketMapper = ticketMapper;
    }

    List<Event> mapToModel(List<EventEntity> eventEntities) {
        return eventEntities.stream()
                .map(this::mapToModel)
                .toList();
    }

    Event mapToModel(EventEntity entity) {
        return Event.builder()
                .id(entity.getEventId())
                .artists(artistMapper.mapToArtists(entity.getArtists()))
                .date(entity.getDate())
                .category(entity.getCategory())
                .location(locationMapper.mapToLocation(entity.getLocation()))
                .name(entity.getName())
                .status(entity.getStatus())
                .availableTickets(ticketMapper.mapToTickets(entity.getTickets()))
                .build();
    }

    public EventEntity mapToEntity(Event event) {
        var eventEntity = new EventEntity();
        eventEntity.setEventId(event.getId());
        eventEntity.setName(event.getName());
        eventEntity.setArtists(artistMapper.mapToEntities(event.getArtists()));
        eventEntity.setDate(event.getDate());
        eventEntity.setCategory(event.getCategory());
        eventEntity.setLocation(locationMapper.mapToEntity(event.getLocation()));
        eventEntity.setStatus(event.getStatus());
        eventEntity.setTickets(ticketMapper.mapToEntities(event.getTickets()));
        return eventEntity;
    }
}
