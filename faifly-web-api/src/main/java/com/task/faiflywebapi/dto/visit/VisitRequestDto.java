package com.task.faiflywebapi.dto.visit;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import java.time.Instant;

@Value
@Builder
@Jacksonized
@JsonInclude
public class VisitRequestDto {
  @NotBlank String patientId;
  @NotBlank String doctorId;
  @NotNull Instant startDateTime;
}
