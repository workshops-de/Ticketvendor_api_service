package de.workshops.ticketvendorapi.model;

import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.format.annotation.DateTimeFormat;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(builder = Event.Builder.class)
public class Event {

    private final @NonNull UUID id;
    private final @NonNull String name;
    private final @Nullable @Valid List<Artist> artists;
    private final @Nullable EventCategory category;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final @NonNull OffsetDateTime date;

    private final @NonNull @Valid Location location;
    private @NonNull List<Ticket> tickets;
    private @NonNull EventStatus status;

    private Event(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.artists = builder.artists;
        this.category = builder.category;
        this.date = builder.date;
        this.location = builder.location;
        this.tickets = new ArrayList<>(builder.availableTickets);
        this.status = builder.status;
    }

    public @NonNull UUID getId() {
        return id;
    }

    public @NonNull String getName() {
        return name;
    }

    public @Nullable List<Artist> getArtists() {
        return artists;
    }

    public @Nullable EventCategory getCategory() {
        return category;
    }

    public @NonNull OffsetDateTime getDate() {
        return date;
    }

    public @NonNull Location getLocation() {
        return location;
    }

    public @NonNull List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public @NonNull EventStatus getStatus() {
        return status;
    }

    public void setStatus(@NonNull EventStatus status) {
        this.status = status;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private UUID id;
        private String name;
        private List<Artist> artists;
        private EventCategory category;
        private OffsetDateTime date;
        private Location location;
        private List<Ticket> availableTickets;
        private EventStatus status;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder artists(List<Artist> artists) {
            if (artists != null) {
                this.artists = List.copyOf(artists);
            }
            return this;
        }

        public Builder category(EventCategory category) {
            this.category = category;
            return this;
        }

        public Builder date(OffsetDateTime date) {
            this.date = date;
            return this;
        }

        public Builder location(Location location) {
            this.location = location;
            return this;
        }

        public Builder availableTickets(List<Ticket> availableTickets) {
            this.availableTickets = List.copyOf(availableTickets);
            return this;
        }

        public Builder status(EventStatus status) {
            this.status = status;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artists=" + artists +
                ", category=" + category +
                ", date=" + date +
                ", location=" + location +
                ", availableTickets=" + tickets +
                ", status=" + status +
                '}';
    }
}

