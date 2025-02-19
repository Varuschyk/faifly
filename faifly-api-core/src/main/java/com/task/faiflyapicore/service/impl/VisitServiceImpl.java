package com.task.faiflyapicore.service.impl;

import com.task.faiflyapicore.mapper.VisitMapper;
import com.task.faiflyapicore.persistence.repository.DoctorRepository;
import com.task.faiflyapicore.persistence.repository.PatientRepository;
import com.task.faiflyapicore.persistence.repository.VisitRepository;
import com.task.faiflyapicore.pojo.visit.VisitReadPojo;
import com.task.faiflyapicore.pojo.visit.VisitWritePojo;
import com.task.faiflyapicore.service.VisitService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

  private final VisitRepository visitRepository;
  private final VisitMapper visitMapper;
  private final DoctorRepository doctorService;
  private final PatientRepository patientService;

  @Override
  public List<VisitReadPojo> getVisits(@Nonnull final Long patientId) {
    final var visits = visitRepository.findAllByPatientId(patientId);
    return visits.stream().map(visitMapper::toVisitReadPojo).toList();
  }

  @Override
  @Transactional
  public VisitReadPojo createVisit(@Nonnull final VisitWritePojo visitWritePojo) {
    final var visitToSave = visitMapper.toVisitEntity(visitWritePojo);
    final var doctor = doctorService.findById(visitWritePojo.getDoctorId())
        .orElseThrow(() -> new RuntimeException("Doctor doesn't exists!"));
    visitToSave.setDoctor(doctor);
    final var patient = patientService.findById(visitWritePojo.getPatientId())
        .orElseThrow(() -> new RuntimeException("Patient doesn't exists!"));
    visitToSave.setEndDateTime(visitWritePojo.getStartDateTime()
        .plus(30, ChronoUnit.MINUTES));
    visitToSave.setPatient(patient);
    final var savedVisit = visitRepository.save(visitToSave);
    return visitMapper.toVisitReadPojo(savedVisit);
  }
}
