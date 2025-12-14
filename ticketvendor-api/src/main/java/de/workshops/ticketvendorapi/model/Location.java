package de.workshops.ticketvendorapi.model;


import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonDeserialize(builder = Location.Builder.class)
public class Location {

    private final @NonNull String name;
    private final @Nullable @Valid Address address;
    private final @Nullable @Valid Geolocation geolocation;

    private Location(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.geolocation = builder.geolocation;
    }

    public @NonNull String getName() {
        return name;
    }

    public @Nullable Address getAddress() {
        return address;
    }

    public @Nullable Geolocation getGeolocation() {
        return geolocation;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private String name;
        private Address address;
        private Geolocation geolocation;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder geolocation(Geolocation geolocation) {
            this.geolocation = geolocation;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name) && Objects.equals(address, location.address) && Objects.equals(geolocation, location.geolocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, geolocation);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", geolocation=" + geolocation +
                '}';
    }
}

