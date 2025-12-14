package de.workshops.ticketvendorservice.application;

import de.workshops.ticketvendorapi.model.Event;
import de.workshops.ticketvendorapi.model.EventCategory;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.UUID;

public interface EventLoadPort {
    List<Event> getAllEvents();

    @NonNull Event getEvent(@NonNull UUID uuid);

    @NonNull List<Event> getEventsByCategory(@NonNull EventCategory eventCategory);

    @NonNull List<Event> getEventsByCity(@NonNull String city);
}
