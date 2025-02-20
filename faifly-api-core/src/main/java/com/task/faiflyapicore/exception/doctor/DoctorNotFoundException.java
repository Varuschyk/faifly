package com.task.faiflyapicore.exception.doctor;

import com.task.faiflyapicore.exception.NotFoundException;
import jakarta.annotation.Nonnull;
import java.io.Serial;

public class DoctorNotFoundException extends NotFoundException {

	@Serial
	private static final long serialVersionUID = 6297331259350797064L;

	public DoctorNotFoundException(@Nonnull final String message) {
		super(message);
	}
}
