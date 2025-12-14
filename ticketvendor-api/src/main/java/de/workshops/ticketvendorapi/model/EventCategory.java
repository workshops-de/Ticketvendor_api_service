package de.workshops.ticketvendorapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Generated;

/**
 * Gets or Sets EventCategory
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-29T20:21:51.530248+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public enum EventCategory {
    MUSIC("music"),
    CIRCUS("circus"),
    THEATER("theater"),
    OPERA("opera"),
    SPORT("sport"),
    OTHER("other");

    private final String value;

    EventCategory(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static EventCategory fromValue(String value) {
        for (EventCategory b : EventCategory.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

