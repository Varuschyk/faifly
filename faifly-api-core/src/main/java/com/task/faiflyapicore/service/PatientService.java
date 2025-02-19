package com.task.faiflyapicore.service;

import com.task.faiflyapicore.pojo.patient.PatientReadPojo;
import jakarta.annotation.Nonnull;

public interface PatientService {

	PatientReadPojo get(@Nonnull Long id);
}
