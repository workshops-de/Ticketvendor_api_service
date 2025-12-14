package de.workshops.ticketvendorapi.model;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.NonNull;

import java.math.BigDecimal;

public record Price(@NonNull @Positive BigDecimal amount, @NonNull @Size(min = 3, max = 3) String currency) {
}

