package de.workshops.ticketvendorservice.adapter.out;

import de.workshops.ticketvendorapi.model.EventCategory;
import de.workshops.ticketvendorapi.model.EventStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="event")
class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID eventId;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "event_artist",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<ArtistEntity> artists;

    private EventCategory category;
    private OffsetDateTime date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<TicketEntity> tickets;

    private EventStatus status;

    void setId(Long id) {
        this.id = id;
    }

    Long getId() {
        return id;
    }

    UUID getEventId() {
        return eventId;
    }

    void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Set<ArtistEntity> getArtists() {
        return artists;
    }

    void setArtists(Set<ArtistEntity> artists) {
        this.artists = artists;
    }

    List<TicketEntity> getTickets() {
        return tickets;
    }

    void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
        if (this.tickets != null) {
            this.tickets.forEach(ticketEntity -> ticketEntity.setEvent(this));
        }
    }

    EventCategory getCategory() {
        return category;
    }

    void setCategory(EventCategory category) {
        this.category = category;
    }

    OffsetDateTime getDate() {
        return date;
    }

    void setDate(OffsetDateTime date) {
        this.date = date;
    }

    LocationEntity getLocation() {
        return location;
    }

    void setLocation(LocationEntity location) {
        this.location = location;
    }

    EventStatus getStatus() {
        return status;
    }

    void setStatus(EventStatus status) {
        this.status = status;
    }
}
