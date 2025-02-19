package com.task.faiflywebapi.dto.visit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.faiflywebapi.dto.doctor.DoctorResponseDto;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

@Value
@Builder
@Jacksonized
@JsonInclude
public class VisitRespDto {
	DoctorResponseDto doctor;
	Instant startDateTime;
	Instant endDateTime;
}
