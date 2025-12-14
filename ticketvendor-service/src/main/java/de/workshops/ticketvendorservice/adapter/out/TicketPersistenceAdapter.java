package de.workshops.ticketvendorservice.adapter.out;

import de.workshops.ticketvendorapi.model.Ticket;
import de.workshops.ticketvendorservice.application.TicketLoadPort;
import de.workshops.ticketvendorservice.application.TicketUpdatePort;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
class TicketPersistenceAdapter implements TicketLoadPort, TicketUpdatePort {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    TicketPersistenceAdapter(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public List<Ticket> loadAllEventTickets(@NonNull UUID eventId) {
        var ticketEntities = ticketRepository.findByEventId(eventId);
        return ticketMapper.mapToTickets(ticketEntities);
    }

    @Override
    public Ticket loadEventTicketByCategory(@NonNull UUID eventId, @Nullable String category) {
        var ticket = ticketRepository.findByEventIdAndCategory(eventId, category);
        return ticketMapper.mapToTicket(ticket);
    }

    @Override
    @Modifying
    public void updateOrderedTickets(@NonNull UUID eventId, @NonNull Integer amountOrdered, @Nullable String category) {
        ticketRepository.updateOrderedTicketNumber(eventId, amountOrdered, category);
    }

    @Override
    @Modifying
    public void updateReservedTickets(@NonNull UUID eventId, @NonNull Integer amountReserved, @Nullable String category) {
        ticketRepository.updateReservedTicketNumber(eventId, amountReserved, category);
    }
}
