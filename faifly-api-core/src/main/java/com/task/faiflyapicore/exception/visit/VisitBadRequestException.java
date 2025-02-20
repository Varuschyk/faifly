package com.task.faiflyapicore.exception.visit;

import com.task.faiflyapicore.exception.BadRequestException;
import jakarta.annotation.Nonnull;

import java.io.Serial;

public class VisitBadRequestException extends BadRequestException {

	@Serial
	private static final long serialVersionUID = 4945735182772801974L;

	public VisitBadRequestException(@Nonnull final String message) {
		super(message);
	}
}
