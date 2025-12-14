package de.workshops.ticketvendorservice.application;

import de.workshops.ticketvendorapi.model.Event;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface EventUseCase {
    @NonNull List<Event> getAllEvents(ListEventQuery query);
    @Nullable Event getById(UUID id);
}
