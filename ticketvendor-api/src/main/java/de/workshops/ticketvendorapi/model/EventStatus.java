package de.workshops.ticketvendorapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EventStatus {
    ANNOUNCED("announced"),
    OPEN("open"),
    SOLD_OUT("sold out");

    private final String value;

    EventStatus(String value) {
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
    public static EventStatus fromValue(String value) {
        for (EventStatus b : EventStatus.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
