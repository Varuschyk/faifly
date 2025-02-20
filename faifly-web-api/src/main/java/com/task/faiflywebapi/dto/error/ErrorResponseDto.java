package com.task.faiflywebapi.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude
public class ErrorResponseDto {

	@NotBlank
	String errorMessage;

	@NotBlank
	String correlationId;
}