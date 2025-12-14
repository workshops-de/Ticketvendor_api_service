package de.workshops.ticketvendorservice.adapter.in;

import de.workshops.ticketvendorapi.TicketVendorApi;
import de.workshops.ticketvendorapi.model.*;
import de.workshops.ticketvendorservice.application.*;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ticketvendor/api")
class TicketVendorController implements TicketVendorApi {
    private final EventUseCase eventUseCase;
    private final TicketOrderUseCase ticketOrderUseCase;
    private final TicketReservationUseCase ticketReservationUseCase;

    TicketVendorController(EventUseCase eventUseCase, TicketOrderUseCase ticketOrderUseCase, TicketReservationUseCase ticketReservationUseCase) {
        this.eventUseCase = eventUseCase;
        this.ticketOrderUseCase = ticketOrderUseCase;
        this.ticketReservationUseCase = ticketReservationUseCase;
    }

    @Override
    public ResponseEntity<List<Event>> getEvents(@Nullable EventCategory category, @Nullable String city) {
        var listEventQuery = new ListEventQuery(category, city);
        var allEvents = eventUseCase.getAllEvents(listEventQuery);
        if (allEvents.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(allEvents);
    }

    @Override
    public ResponseEntity<Event> getEvent(UUID eventId) {
        var event = eventUseCase.getById(eventId);
        if (event == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(event);
    }

    @Override
    public ResponseEntity<TicketOrderResponse> orderEventTickets(UUID eventId, TicketOrderRequest ticketOrderRequest) {
        var ticketOrderCommand = new TicketOrderCommand(eventId, ticketOrderRequest.category(), ticketOrderRequest.number());
        var result = ticketOrderUseCase.orderTickets(ticketOrderCommand);

        var ticketOrderResponse = new TicketOrderResponse(result.id(), result.date(), result.number(), result.singlePrice(), result.totalPrice());

        return ResponseEntity.ok(ticketOrderResponse);
    }

    @Override
    public ResponseEntity<TicketReservationResponse> reserveEventTickets(UUID eventId, TicketReservationRequest ticketReservationRequest) {
        var ticketReservationCommand = new TicketReservationCommand(eventId, ticketReservationRequest.category(), ticketReservationRequest.number());
        var result = ticketReservationUseCase.reserveTickets(ticketReservationCommand);

        var ticketReservationResponse = new TicketReservationResponse(result.id(), result.date(), result.number(), result.singlePrice(), result.totalPrice());

        return ResponseEntity.ok(ticketReservationResponse);
    }

    @ExceptionHandler(TicketException.class)
    ResponseEntity<TicketOrderError> handleTicketOrderException(TicketException exception) {
        var build = TicketOrderError.builder()
                .reason(exception.getReason())
                .detail(exception.getDetail())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(build);
    }
}
