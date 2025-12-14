package de.workshops.ticketvendorservice.application;

import de.workshops.ticketvendorapi.model.Price;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketReservationResult(UUID id, LocalDateTime date, Integer number, Price singlePrice, Price totalPrice) {
}
