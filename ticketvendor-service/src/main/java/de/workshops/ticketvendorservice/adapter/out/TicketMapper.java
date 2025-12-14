package de.workshops.ticketvendorservice.adapter.out;

import de.workshops.ticketvendorapi.model.Price;
import de.workshops.ticketvendorapi.model.Ticket;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TicketMapper {
    List<Ticket> mapToTickets(List<TicketEntity> ticketEntities) {
        return ticketEntities.stream()
                .map(this::mapToTicket)
                .toList();
    }

    Ticket mapToTicket(TicketEntity ticketEntity) {
        return Ticket.builder()
                .category(ticketEntity.getCategory())
                .totalNumber(ticketEntity.getAmountTotal())
                .ordered(ticketEntity.getAmountOrdered())
                .reserved(ticketEntity.getAmountReserved())
                .price(new Price(ticketEntity.getPriceAmount(), ticketEntity.getCurrency()))
                .build();
    }

    List<TicketEntity> mapToEntities(@NonNull List<Ticket> availableTickets) {
        return availableTickets.stream()
                .map(this::mapToEntity)
                .toList();
    }

    TicketEntity mapToEntity(Ticket ticket) {
        var ticketEntity = new TicketEntity();
        ticketEntity.setCategory(ticket.getCategory());
        ticketEntity.setCurrency(ticket.getPrice().currency());
        ticketEntity.setPriceAmount(ticket.getPrice().amount());
        ticketEntity.setAmountTotal(ticket.getTotalNumber());
        ticketEntity.setAmountOrdered(ticket.getOrdered());
        ticketEntity.setAmountReserved(ticket.getReserved());
        return ticketEntity;
    }
}
