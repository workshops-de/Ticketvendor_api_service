package de.workshops.ticketvendorservice.adapter.out;

import de.workshops.ticketvendorapi.model.EventCategory;
import de.workshops.ticketvendorapi.model.EventStatus;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface EventRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findByEventId(UUID eventId);

    @Modifying
    @Query("update EventEntity event set event.status = :status where event.eventId = :eventId")
    void updateEventStatus(UUID eventId, EventStatus status);

    List<EventEntity> findByCategory(@NonNull EventCategory eventCategory);

    @Query("select event from EventEntity event where event.location is not null and event.location.address like concat('%\"city\":\"', :city, '\"%') ")
    List<EventEntity> findByCity(@NonNull String city);
}
