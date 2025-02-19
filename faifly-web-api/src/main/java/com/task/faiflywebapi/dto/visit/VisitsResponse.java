package com.task.faiflywebapi.dto.visit;

import jakarta.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class VisitsResponse {
  private final String firstName;
  private final String lastName;
  private final List<VisitRespDto> visits;
  private final int count;

  public VisitsResponse(@Nonnull final String firstName,
                        @Nonnull final String lastName,
                        @Nonnull final List<VisitRespDto> visits) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.visits = visits;
    this.count = visits.size();
  }
}

