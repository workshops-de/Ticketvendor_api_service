package de.workshops.ticketvendorservice.application;

import de.workshops.ticketvendorapi.model.Ticket;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface TicketLoadPort {
    List<Ticket> loadAllEventTickets(@NonNull UUID eventId);
    Ticket loadEventTicketByCategory(@NonNull UUID eventId, @Nullable String category);
}
