package de.workshops.ticketvendorapi.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonDeserialize(builder = Ticket.Builder.class)
public class Ticket {
    private final @NonNull
    @PositiveOrZero Integer totalNumber;
    private final @NonNull
    @PositiveOrZero Integer reserved;
    private final @NonNull
    @PositiveOrZero Integer ordered;
    private final @Nullable String category;
    private final @NonNull
    @Valid Price price;

    private Ticket(Builder builder) {
        this.totalNumber = builder.totalNumber;
        this.reserved = builder.reserved;
        this.ordered = builder.ordered;
        this.category = builder.category;
        this.price = builder.price;
    }

    public @NonNull Integer getTotalNumber() {
        return totalNumber;
    }

    public @NonNull Integer getReserved() {
        return reserved;
    }

    public @NonNull Integer getOrdered() {
        return ordered;
    }

    public @Nullable String getCategory() {
        return category;
    }

    public @NonNull Price getPrice() {
        return price;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getNumberBookable() {
        return totalNumber - ordered - reserved;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private Integer totalNumber;
        private Integer reserved;
        private Integer ordered;
        private String category;
        private Price price;

        public Builder totalNumber(Integer totalNumber) {
            this.totalNumber = totalNumber;
            return this;
        }

        public Builder reserved(Integer reserved) {
            this.reserved = reserved;
            return this;
        }

        public Builder ordered(Integer ordered) {
            this.ordered = ordered;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder price(Price price) {
            this.price = price;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(totalNumber, ticket.totalNumber) && Objects.equals(reserved, ticket.reserved) && Objects.equals(ordered, ticket.ordered) && Objects.equals(category, ticket.category) && Objects.equals(price, ticket.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalNumber, reserved, ordered, category, price);
    }
}

