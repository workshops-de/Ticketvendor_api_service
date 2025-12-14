package de.workshops.ticketvendorapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonDeserialize(builder = TicketOrderError.Builder.class)
public class TicketOrderError {
  private final @NonNull String reason;
  private final @Nullable String detail;

  private TicketOrderError(Builder builder) {
    this.reason = builder.reason;
    this.detail = builder.detail;
  }

  @JsonProperty("reason")
  public @NonNull String getReason() {
    return reason;
  }
  @JsonProperty("detail")
  public @Nullable String getDetail() {
    return detail;
  }

  public static Builder builder() {
    return new Builder();
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {
    private String reason;
    private String detail;

    public Builder reason(String reason) {
      this.reason = reason;
      return this;
    }
    public Builder detail(String detail) {
      this.detail = detail;
      return this;
    }
    public TicketOrderError build() {
      return new TicketOrderError(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    TicketOrderError that = (TicketOrderError) o;
    return Objects.equals(reason, that.reason) && Objects.equals(detail, that.detail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reason, detail);
  }

  @Override
  public String toString() {
    return "TicketOrderError{" +
            "reason='" + reason + '\'' +
            ", detail='" + detail + '\'' +
            '}';
  }
}

