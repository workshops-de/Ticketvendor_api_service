package de.workshops.ticketvendorservice.adapter.out;

import de.workshops.ticketvendorapi.model.Artist;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class ArtistMapper {
    List<Artist> mapToArtists(Set<ArtistEntity> artists) {
        return artists.stream()
                .map(this::mapToArtist)
                .toList();
    }

    Artist mapToArtist(ArtistEntity artistEntity) {
        return Artist.builder()
                .name(artistEntity.getName())
                .build();
    }

    Set<ArtistEntity> mapToEntities(@Nullable List<Artist> artists) {
        if (artists == null) {
            return null;
        }
        return artists.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toSet());
    }

    ArtistEntity mapToEntity(Artist artist) {
        var artistEntity = new ArtistEntity();
        artistEntity.setName(artist.getName());
        return artistEntity;
    }
}
