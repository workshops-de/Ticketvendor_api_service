package de.workshops.ticketvendorservice.adapter.out;

import jakarta.persistence.*;

@Entity
@Table(name = "artist")
class ArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    void setId(Long id) {
        this.id = id;
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
