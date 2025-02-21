package com.task.faiflyapicore.service;

import com.task.faiflyapicore.exception.doctor.DoctorNotFoundException;
import com.task.faiflyapicore.exception.patient.PatientNotFoundException;
import com.task.faiflyapicore.exception.visit.VisitBadRequestException;
import com.task.faiflyapicore.mapper.visit.VisitMapper;
import com.task.faiflyapicore.persistence.enity.DoctorEntity;
import com.task.faiflyapicore.persistence.enity.PatientEntity;
import com.task.faiflyapicore.persistence.enity.VisitEntity;
import com.task.faiflyapicore.persistence.repository.DoctorRepository;
import com.task.faiflyapicore.persistence.repository.PatientRepository;
import com.task.faiflyapicore.persistence.repository.VisitRepository;
import com.task.faiflyapicore.pojo.doctor.DoctorReadPojo;
import com.task.faiflyapicore.pojo.patient.PatientReadPojo;
import com.task.faiflyapicore.pojo.visit.VisitReadPojo;
import com.task.faiflyapicore.pojo.visit.VisitWritePojo;
import com.task.faiflyapicore.service.visit.impl.VisitServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.atLeast;

@ExtendWith(MockitoExtension.class)
public class VisitServiceImplTests {

  private static final Instant START_DATE = Instant.now();
  private static final Instant END_DATE = Instant.now().plusSeconds(5000);
  private static final int PAGE = 1;
  private static final int SIZE = 5;
  private static final Long PATIENT_ID = 1L;
  private static final String PATIENT_NAME = "testPatientName";
  private static final String PATIENT_LASTNAME = "testPatientLastname";
  private static final String DOCTOR_NAME = "testDoctorName";
  private static final String DOCTOR_LASTNAME = "testDoctorLastName";
  private static final Long DOCTOR_ID_1 = 1L;
  private static final Set<Long> DOCTOR_IDS = Set.of(DOCTOR_ID_1);
  private static final PatientEntity PATIENT_ENTITY = PatientEntity.builder()
      .id(PATIENT_ID)
      .firstName(PATIENT_NAME)
      .lastName(PATIENT_LASTNAME)
      .build();
  private static final DoctorEntity DOCTOR_ENTITY = DoctorEntity.builder()
      .id(DOCTOR_ID_1)
      .firstName(DOCTOR_NAME)
      .lastName(DOCTOR_LASTNAME)
      .timeZone(TimeZone.getTimeZone("UTC"))
      .build();
  private static final Long VISIT_ID = 1L;
  private static final VisitEntity VISIT_ENTITY = VisitEntity.builder()
      .id(VISIT_ID)
      .startDateTime(START_DATE)
      .endDateTime(END_DATE)
      .patient(PATIENT_ENTITY)
      .doctor(DOCTOR_ENTITY)
      .build();
  private static final DoctorReadPojo DOCTOR_READ_POJO = DoctorReadPojo.builder()
      .firstName(DOCTOR_NAME)
      .lastName(DOCTOR_LASTNAME)
      .build();
  private static final PatientReadPojo PATIENT_READ_POJO = PatientReadPojo.builder()
      .firstName(PATIENT_NAME)
      .lastName(PATIENT_LASTNAME)
      .build();
  private static final VisitReadPojo VISIT_READ_POJO = VisitReadPojo.builder()
      .startDateTime(START_DATE)
      .endDateTime(END_DATE)
      .doctor(DOCTOR_READ_POJO)
      .patient(PATIENT_READ_POJO)
      .build();
  private static final VisitWritePojo VISIT_WRITE_POJO = VisitWritePojo.builder()
      .startDateTime(START_DATE)
      .endDateTime(END_DATE)
      .doctorId(DOCTOR_ID_1)
      .patientId(PATIENT_ID)
      .build();

  @Mock private VisitRepository visitRepository;
  @Mock private VisitMapper visitMapper;
  @Mock private DoctorRepository doctorRepository;
  @Mock private PatientRepository patientRepository;

  @InjectMocks
  private VisitServiceImpl visitService;

  @Test
  public void shouldGetVisitsByPatientNameAndDoctorsThatRelatesTo() {
    when(visitRepository.findAllByPatientFirstNameAndDoctorIdIn(
        PATIENT_NAME, DOCTOR_IDS, PageRequest.of(PAGE, SIZE))
    ).thenReturn(new PageImpl<>(List.of(VISIT_ENTITY)));
    when(visitMapper.toVisitReadPojo(VISIT_ENTITY))
        .thenReturn(VISIT_READ_POJO);

    final var result = visitService.getVisits(PAGE, SIZE, PATIENT_NAME, DOCTOR_IDS);
    assertNotNull(result);
    assertEquals(1, result.size());

    verify(visitRepository, only()).findAllByPatientFirstNameAndDoctorIdIn(
        PATIENT_NAME, DOCTOR_IDS, PageRequest.of(PAGE, SIZE));
    verify(visitMapper, atLeast(1))
        .toVisitReadPojo(VISIT_ENTITY);
  }

  @Test
  public void shouldCreateVisit() {
    when(doctorRepository.findById(DOCTOR_ID_1))
        .thenReturn(Optional.of(DOCTOR_ENTITY));
    when(patientRepository.findById(PATIENT_ID))
        .thenReturn(Optional.of(PATIENT_ENTITY));
    when(visitRepository.existsByDoctorIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqual(
        DOCTOR_ID_1, START_DATE, END_DATE))
        .thenReturn(false);
    when(visitMapper.toVisitEntity(VISIT_WRITE_POJO))
        .thenReturn(VISIT_ENTITY);
    when(visitRepository.save(any(VisitEntity.class)))
        .thenReturn(VISIT_ENTITY);
    when(visitMapper.toVisitReadPojo(VISIT_ENTITY))
        .thenReturn(VISIT_READ_POJO);

    final var result = visitService.createVisit(VISIT_WRITE_POJO);
    assertNotNull(result);
    assertEquals(VISIT_READ_POJO, result);

    verify(doctorRepository, only()).findById(DOCTOR_ID_1);
    verify(patientRepository, only()).findById(PATIENT_ID);
    verify(visitRepository, times(1))
        .existsByDoctorIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqual(
        DOCTOR_ID_1, START_DATE, END_DATE);
    verify(visitMapper, times(1))
        .toVisitEntity(VISIT_WRITE_POJO);
    verify(visitRepository, times(1))
        .save(any(VisitEntity.class));
    verify(visitMapper, times(1))
        .toVisitReadPojo(VISIT_ENTITY);
  }

  @Test
  public void shouldThrow_DoctorNotFoundException_ByCreatingVisit() {
    when(doctorRepository.findById(DOCTOR_ID_1))
        .thenReturn(Optional.empty());

    assertThrows(DoctorNotFoundException.class, () ->
        visitService.createVisit(VISIT_WRITE_POJO));

    verify(doctorRepository, only()).findById(DOCTOR_ID_1);
  }

  @Test
  public void shouldThrow_PatientNotFoundException_ByCreatingVisit() {
    when(doctorRepository.findById(DOCTOR_ID_1))
        .thenReturn(Optional.of(DOCTOR_ENTITY));
    when(patientRepository.findById(PATIENT_ID))
        .thenReturn(Optional.empty());

    assertThrows(PatientNotFoundException.class, () ->
        visitService.createVisit(VISIT_WRITE_POJO));

    verify(doctorRepository, only()).findById(DOCTOR_ID_1);
    verify(patientRepository, only()).findById(PATIENT_ID);
  }

  @Test
  public void shouldThrow_VisitBadRequestException_IfDoctorAlreadyHasVisitOnSpecifiedTime() {
    when(doctorRepository.findById(DOCTOR_ID_1))
        .thenReturn(Optional.of(DOCTOR_ENTITY));
    when(patientRepository.findById(PATIENT_ID))
        .thenReturn(Optional.of(PATIENT_ENTITY));
    when(visitRepository.existsByDoctorIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqual(
        DOCTOR_ID_1, START_DATE, END_DATE))
        .thenReturn(true);

    assertThrows(VisitBadRequestException.class, () ->
        visitService.createVisit(VISIT_WRITE_POJO));

    verify(doctorRepository, only()).findById(DOCTOR_ID_1);
    verify(patientRepository, only()).findById(PATIENT_ID);
    verify(visitRepository, times(1))
        .existsByDoctorIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqual(
            DOCTOR_ID_1, START_DATE, END_DATE);
  }
}
