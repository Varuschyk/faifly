package com.task.faiflyapicore.service.visit.impl;

import com.task.faiflyapicore.exception.doctor.DoctorNotFoundException;
import com.task.faiflyapicore.exception.patient.PatientNotFoundException;
import com.task.faiflyapicore.exception.visit.VisitBadRequestException;
import com.task.faiflyapicore.mapper.visit.VisitMapper;
import com.task.faiflyapicore.persistence.repository.DoctorRepository;
import com.task.faiflyapicore.persistence.repository.PatientRepository;
import com.task.faiflyapicore.persistence.repository.VisitRepository;
import com.task.faiflyapicore.pojo.visit.VisitReadPojo;
import com.task.faiflyapicore.pojo.visit.VisitWritePojo;
import com.task.faiflyapicore.service.VisitService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

  private final VisitRepository visitRepository;
  private final VisitMapper visitMapper;
  private final DoctorRepository doctorService;
  private final PatientRepository patientService;

  @Override
  @Transactional(readOnly = true)
  public List<VisitReadPojo> getVisits(final int page, final int size,
                                       @Nonnull final String patientFirstName,
                                       @Nonnull final Set<Long> doctorIds) {
    final var visits = visitRepository
        .findAllByPatientFirstNameAndDoctorIdIn(patientFirstName, doctorIds, PageRequest.of(page, size)).get();
    return visits.map(visitMapper::toVisitReadPojo).toList();
  }

  @Override
  @Transactional
  public VisitReadPojo createVisit(@Nonnull final VisitWritePojo visitWritePojo) {
    final var doctorId = visitWritePojo.getDoctorId();
    final var doctor = doctorService.findById(doctorId)
        .orElseThrow(() -> new DoctorNotFoundException("Doctor doesn't exists!"));
    final var patient = patientService.findById(visitWritePojo.getPatientId())
        .orElseThrow(() -> new PatientNotFoundException("Patient doesn't exists!"));

    final var startDateTime =
        getInstantAccordingTimeZone(visitWritePojo.getStartDateTime(), doctor.getTimeZone());
    final var endDateTime =
        getInstantAccordingTimeZone(visitWritePojo.getEndDateTime(), doctor.getTimeZone());

    final var doesDoctorHasVisitOnCurrentDateTime = visitRepository
        .existsByDoctorIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqual(doctorId, startDateTime, endDateTime);

    if (doesDoctorHasVisitOnCurrentDateTime) {
      throw new VisitBadRequestException("Doctor already has visit on current time!");
    }

    final var visitToSave = visitMapper.toVisitEntity(visitWritePojo);

    visitToSave.setDoctor(doctor);
    visitToSave.setPatient(patient);
    visitToSave.setStartDateTime(startDateTime);
    visitToSave.setEndDateTime(endDateTime);

    final var savedVisit = visitRepository.save(visitToSave);
    return visitMapper.toVisitReadPojo(savedVisit);
  }

  @Nonnull
  private Instant getInstantAccordingTimeZone(@Nonnull final Instant date,
                                              @Nonnull final TimeZone timeZone) {
    return date.atZone(timeZone.toZoneId()).toInstant();
  }
}
