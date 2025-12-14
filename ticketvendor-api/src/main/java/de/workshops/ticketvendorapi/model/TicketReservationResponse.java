package de.workshops.ticketvendorapi.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketReservationResponse(@NonNull UUID id, @NonNull LocalDateTime date, @NonNull @Positive Integer number, @NonNull @Valid Price pricePerTicket,
                                        @NonNull @Valid Price priceTotal) {
}

