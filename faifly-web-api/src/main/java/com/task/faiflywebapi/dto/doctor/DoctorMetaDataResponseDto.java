package com.task.faiflywebapi.dto.doctor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude
public class DoctorMetaDataResponseDto {
  String firstName;
  String lastName;
  Integer totalPatients;
}
