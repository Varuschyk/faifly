package com.task.faiflywebapi.dto.visit;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class VisitsResponse {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private final String firstName;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private final String lastName;
  private final List<VisitRespDto> visits;
  private final int count;

  public VisitsResponse(@Nullable final String firstName,
                        @Nullable final String lastName,
                        @Nonnull final List<VisitRespDto> visits) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.visits = visits;
    this.count = visits.size();
  }
}

