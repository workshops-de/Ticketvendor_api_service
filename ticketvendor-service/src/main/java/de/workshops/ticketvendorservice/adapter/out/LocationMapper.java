package de.workshops.ticketvendorservice.adapter.out;

import de.workshops.ticketvendorapi.model.Address;
import de.workshops.ticketvendorapi.model.Geolocation;
import de.workshops.ticketvendorapi.model.Location;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

@Component
class LocationMapper {
    Location mapToLocation(LocationEntity locationEntity) {
        return Location.builder()
                .name(locationEntity.getName())
                .geolocation(new Geolocation(locationEntity.getLatitude(), locationEntity.getLongitude()))
                .address(Address.parseFromString(locationEntity.getAddress()))
                .build();
    }

    LocationEntity mapToEntity(@NonNull Location location) {
        var locationEntity = new LocationEntity();
        if (location.getAddress() != null) {
            locationEntity.setAddress(JsonMapper.builder().build().writeValueAsString(location.getAddress()));
        }
        locationEntity.setName(location.getName());
        if (location.getGeolocation() != null) {
            locationEntity.setLatitude(location.getGeolocation().latitude());
            locationEntity.setLongitude(location.getGeolocation().longitude());
        }
        return locationEntity;
    }
}
