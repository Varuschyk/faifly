package com.task.faiflywebapi.dto.patient;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude
public class PatientResponseDto {
  String firstName;
  String lastName;
}
