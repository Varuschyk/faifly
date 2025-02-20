package com.task.faiflyapicore.exception;

import jakarta.annotation.Nonnull;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 3695235041376513180L;

	public NotFoundException(@Nonnull final String message) {
		super(message);
	}
}
