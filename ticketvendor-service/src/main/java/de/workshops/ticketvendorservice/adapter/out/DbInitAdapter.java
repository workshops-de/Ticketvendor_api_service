package de.workshops.ticketvendorservice.adapter.out;

import de.workshops.ticketvendorservice.application.DbInitPort;
import de.workshops.ticketvendorapi.model.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;
import java.util.UUID;

@Component
class DbInitAdapter implements DbInitPort {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    DbInitAdapter(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public void initDb() {
        var davidCopperfield = Artist.builder()
                .name("David Copperfield")
                .build();

        var friedrichstadtpalast = Location.builder()
                .name("Friedrichstadtpalast")
                .address(Address.builder()
                        .city("Berlin")
                        .build())
                .build();


        var magicShow = Event.builder()
                .artists(List.of(davidCopperfield))
                .status(EventStatus.OPEN)
                .id(UUID.randomUUID())
                .location(friedrichstadtpalast)
                .date(OffsetDateTime.of(Year.now().getValue(), Month.DECEMBER.getValue(), 31, 0, 0, 0, 0, ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now())))
                .category(EventCategory.THEATER)
                .availableTickets(List.of(
                        Ticket.builder()
                                .category("Category 1")
                                .price(new Price(BigDecimal.TEN, "EUR"))
                                .totalNumber(10)
                                .build(),
                        Ticket.builder()
                                .category("Category 2")
                                .price(new Price(BigDecimal.TWO, "EUR"))
                                .totalNumber(100)
                                .build(),
                        Ticket.builder()
                                .category("Category 3")
                                .price(new Price(BigDecimal.ONE, "EUR"))
                                .totalNumber(1000)
                                .build())
                )
                .build();

        var eventEntity = eventMapper.mapToEntity(magicShow);
        eventRepository.save(eventEntity);
    }
}
