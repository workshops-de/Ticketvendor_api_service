package de.workshops.ticketvendorapi.model;

import org.jspecify.annotations.NonNull;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonDeserialize(builder = Artist.Builder.class)
public class Artist {

    private final @NonNull String name;

    private Artist(Builder builder) {
        this.name = builder.name;
    }

    public @NonNull String getName() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private String name;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Artist build() {
            return new Artist(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                '}';
    }
}

