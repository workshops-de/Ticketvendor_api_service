package de.workshops.ticketvendorapi.model;


import jakarta.validation.constraints.Positive;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record TicketOrderRequest(@Nullable String category, @NonNull @Positive Integer number) {
}

