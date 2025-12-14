package de.workshops.ticketvendorservice.application;

import de.workshops.ticketvendorapi.model.EventCategory;
import org.jspecify.annotations.Nullable;

public record ListEventQuery(@Nullable EventCategory eventCategory, @Nullable String city) {
}
