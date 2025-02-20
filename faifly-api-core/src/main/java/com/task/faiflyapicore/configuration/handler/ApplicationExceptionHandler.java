package com.task.faiflyapicore.configuration.handler;

import com.task.faiflyapicore.exception.BadRequestException;
import com.task.faiflyapicore.exception.NotFoundException;
import com.task.faiflywebapi.dto.error.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler {

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponseDto handleNotFoundException(@NonNull final HttpServletRequest request,
																									@NonNull final NotFoundException exception) {
		final var correlationId = UUID.randomUUID();
		log.error("Can not find the requested resource. The correlation id={}.", correlationId, exception);
		return ErrorResponseDto.builder()
				.errorMessage("Can not find the requested resource.")
				.correlationId(correlationId.toString())
				.build();
	}

	@ResponseBody
	@ExceptionHandler({BadRequestException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponseDto handleDuplicateException(
			@NonNull final HttpServletRequest request,
			@NonNull final Exception exception) {
		final var correlationId = UUID.randomUUID();
		log.error("The request is malformed. Correlation id={}.",
				correlationId, exception);
		return ErrorResponseDto.builder()
				.errorMessage("The request is malformed.")
				.correlationId(correlationId.toString())
				.build();
	}
}
