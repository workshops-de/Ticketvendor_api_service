package de.workshops.ticketvendorapi.model;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;
import tools.jackson.databind.json.JsonMapper;

import java.util.Objects;

@JsonDeserialize(builder = Address.Builder.class)
public class Address {

    private final @NonNull String city;
    private final @Nullable String postcode;
    private final @Nullable String address;

    private Address(Builder builder) {
        this.city = builder.city;
        this.postcode = builder.postcode;
        this.address = builder.address;
    }

    public @NonNull String getCity() {
        return city;
    }

    public @Nullable String getPostcode() {
        return postcode;
    }

    public @Nullable String getAddress() {
        return address;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private String city;
        private String postcode;
        private String address;

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder postcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    public static Address parseFromString(String addressString) {
        var mapper = JsonMapper.builder().build();
        return mapper.readerFor(Address.class)
                .readValue(addressString);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(city, address1.city) && Objects.equals(postcode, address1.postcode) && Objects.equals(address, address1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, postcode, address);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

