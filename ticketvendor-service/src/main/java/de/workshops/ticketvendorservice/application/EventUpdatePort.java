package de.workshops.ticketvendorservice.application;

import de.workshops.ticketvendorapi.model.EventStatus;

import java.util.UUID;

public interface EventUpdatePort {
    void setEventStatus(UUID eventId, EventStatus eventStatus);
}
