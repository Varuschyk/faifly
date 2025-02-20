package com.task.faiflyapicore.exception;

import jakarta.annotation.Nonnull;
import java.io.Serial;

public class BadRequestException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 7837779246398891065L;

	public BadRequestException(@Nonnull final String message) {
		super(message);
	}
}
