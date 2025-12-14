package de.workshops.ticketvendorservice.application;

import de.workshops.ticketvendorapi.model.Event;
import de.workshops.ticketvendorapi.model.EventCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    @Mock
    EventLoadPort eventLoadPort;

    @Mock
    EventUpdatePort eventUpdatePort;

    @InjectMocks
    EventService eventService;

    @Test
    void getAllEvents_noFilter() {
        var listEventQuery = new ListEventQuery(null, null);

        when(eventLoadPort.getAllEvents()).thenReturn(List.of(createValidEvent()));

        var allEvents = eventService.getAllEvents(listEventQuery);

        assertThat(allEvents).hasSize(1);

        verify(eventLoadPort, never()).getEventsByCity(anyString());
        verify(eventLoadPort, never()).getEventsByCategory(any(EventCategory.class));
    }

    @Test
    void getAllEvents_cityFilter() {
        var listEventQuery = new ListEventQuery(null, "Berlin");

        when(eventLoadPort.getEventsByCity(anyString())).thenReturn(List.of(createValidEvent()));

        var allEvents = eventService.getAllEvents(listEventQuery);

        assertThat(allEvents).hasSize(1);

        verify(eventLoadPort, never()).getAllEvents();
        verify(eventLoadPort, never()).getEventsByCategory(any(EventCategory.class));
    }

    @Test
    void getAllEvents_categoryFilter() {
        var listEventQuery = new ListEventQuery(EventCategory.MUSIC, null);

        when(eventLoadPort.getEventsByCategory(any(EventCategory.class))).thenReturn(List.of(createValidEvent()));

        var allEvents = eventService.getAllEvents(listEventQuery);

        assertThat(allEvents).hasSize(1);

        verify(eventLoadPort, never()).getAllEvents();
        verify(eventLoadPort, never()).getEventsByCity(anyString());
    }

    @Test
    void getAllEvents_allFilter() {
        var theaterEventInBerlin = createValidEvent();
        var musicEventEventInBerlin = createValidEvent();
        var theaterEventInDresden = createValidEvent();

        var listEventQuery = new ListEventQuery(EventCategory.MUSIC, "Berlin");

        when(eventLoadPort.getEventsByCategory(any(EventCategory.class))).thenReturn(List.of(theaterEventInBerlin, theaterEventInDresden));
        when(eventLoadPort.getEventsByCity(anyString())).thenReturn(List.of(theaterEventInBerlin, musicEventEventInBerlin));

        var allEvents = eventService.getAllEvents(listEventQuery);

        assertThat(allEvents).hasSize(1)
                .first()
                .hasFieldOrPropertyWithValue("id", theaterEventInBerlin.getId());

        verify(eventLoadPort, never()).getAllEvents();
    }

    private Event createValidEvent() {
        return Event.builder()
                .id(UUID.randomUUID())
                .availableTickets(List.of())
                .build();
    }

}