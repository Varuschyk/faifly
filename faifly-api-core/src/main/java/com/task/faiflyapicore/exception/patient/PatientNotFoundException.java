package com.task.faiflyapicore.exception.patient;

import com.task.faiflyapicore.exception.NotFoundException;
import jakarta.annotation.Nonnull;
import java.io.Serial;

public class PatientNotFoundException extends NotFoundException {

	@Serial
	private static final long serialVersionUID = -6473113003569047714L;

	public PatientNotFoundException(@Nonnull final String message) {
		super(message);
	}
}
