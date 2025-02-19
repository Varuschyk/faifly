package com.task.faiflyapicore.service.patient.impl;

import com.task.faiflyapicore.mapper.patient.PatientMapper;
import com.task.faiflyapicore.persistence.repository.PatientRepository;
import com.task.faiflyapicore.pojo.patient.PatientReadPojo;
import com.task.faiflyapicore.service.PatientService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;
	private final PatientMapper patientMapper;

	@Override
	public PatientReadPojo get(@Nonnull final Long id) {
		final var patient = patientRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Patient not found"));
		return patientMapper.toPatientReadPojo(patient);
	}
}
