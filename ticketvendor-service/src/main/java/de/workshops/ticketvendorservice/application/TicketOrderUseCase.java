package de.workshops.ticketvendorservice.application;

public interface TicketOrderUseCase {
    TicketOrderResult orderTickets(TicketOrderCommand ticketOrderCommand) throws TicketException;
}
