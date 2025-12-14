package de.workshops.ticketvendorapi.model;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class ErrorModel {
    private final @NonNull String name;
    private final @Nullable String detail;

    private ErrorModel(Builder builder) {
        this.name = builder.name;
        this.detail = builder.detail;
    }

    public @NonNull String getName() {
        return name;
    }

    public @Nullable String getDetail() {
        return detail;
    }

    public static final class Builder {
        private String name;
        private String detail;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public ErrorModel build() {
            return new ErrorModel(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ErrorModel that = (ErrorModel) o;
        return Objects.equals(name, that.name) && Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, detail);
    }

    @Override
    public String toString() {
        return "ErrorModel{" +
                "name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}

