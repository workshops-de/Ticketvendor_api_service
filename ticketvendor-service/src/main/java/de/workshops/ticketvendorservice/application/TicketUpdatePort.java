package de.workshops.ticketvendorservice.application;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public interface TicketUpdatePort {
    void updateOrderedTickets(@NonNull UUID eventId, @NonNull Integer amountOrdered, @Nullable String category);
    void updateReservedTickets(@NonNull UUID eventId, @NonNull Integer amountReserved, @Nullable String category);
}
