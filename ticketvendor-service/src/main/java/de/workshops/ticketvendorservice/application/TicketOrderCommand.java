package de.workshops.ticketvendorservice.application;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public record TicketOrderCommand(@NonNull UUID eventId, @Nullable String category, @NonNull Integer amount) {
}
