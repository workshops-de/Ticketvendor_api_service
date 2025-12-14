package de.workshops.ticketvendorapi.model;

import org.jspecify.annotations.NonNull;

public record Geolocation (@NonNull Double latitude, @NonNull Double longitude) { }

