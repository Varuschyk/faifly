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
  private final List<VisitResponseDto> visits;
  private final int count;

  public VisitsResponse(@Nonnull final List<VisitResponseDto> visits) {
    this.visits = visits;
    this.count = visits.size();
  }
}

