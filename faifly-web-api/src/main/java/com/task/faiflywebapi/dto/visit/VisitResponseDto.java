package com.task.faiflywebapi.dto.visit;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import java.time.Instant;

@Value
@Builder
@Jacksonized
@JsonInclude
public class VisitResponseDto {
  String patientId;
  String doctorId;
  Instant startDateTime;
  Instant endDateTime;
}
