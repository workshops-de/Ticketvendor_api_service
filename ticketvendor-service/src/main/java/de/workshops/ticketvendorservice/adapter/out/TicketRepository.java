package de.workshops.ticketvendorservice.adapter.out;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    @Query("select ticket from TicketEntity ticket where ticket.event.eventId = :eventId")
    List<TicketEntity> findByEventId(@NonNull UUID eventId);

    @Query("select ticket from TicketEntity ticket where ticket.event.eventId = :eventId and ((:category is null and ticket.category is null) or (:category is not null and ticket.category = :category))")
    TicketEntity findByEventIdAndCategory(@NonNull UUID eventId, @Nullable String category);

    @Modifying
    @Query("update TicketEntity ticket set ticket.amountOrdered = :amount where ticket.event.eventId = :eventId and ((:category is null and ticket.category is null) or (:category is not null and ticket.category = :category))")
    TicketEntity updateOrderedTicketNumber(@NonNull UUID eventId, @NonNull Integer amount, @Nullable String category);

    @Modifying
    @Query("update TicketEntity ticket set ticket.amountReserved = :amount where ticket.event.eventId = :eventId and ((:category is null and ticket.category is null) or (:category is not null and ticket.category = :category))")
    TicketEntity updateReservedTicketNumber(@NonNull UUID eventId, @NonNull Integer amount, @Nullable String category);
}

