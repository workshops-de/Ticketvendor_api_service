package de.workshops.ticketvendorservice.application;

public interface TicketReservationUseCase {
    TicketReservationResult reserveTickets(TicketReservationCommand ticketReservationCommand) throws TicketException;
}
