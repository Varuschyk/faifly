package com.task.faiflyapicore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.task.faiflyapicore.configuration.handler.ApplicationExceptionHandler;
import com.task.faiflyapicore.mapper.visit.VisitMapper;
import com.task.faiflyapicore.pojo.doctor.DoctorReadPojo;
import com.task.faiflyapicore.pojo.patient.PatientReadPojo;
import com.task.faiflyapicore.pojo.visit.VisitReadPojo;
import com.task.faiflyapicore.pojo.visit.VisitWritePojo;
import com.task.faiflyapicore.service.VisitService;
import com.task.faiflywebapi.dto.doctor.DoctorMetaDataResponseDto;
import com.task.faiflywebapi.dto.doctor.DoctorResponseDto;
import com.task.faiflywebapi.dto.patient.PatientResponseDto;
import com.task.faiflywebapi.dto.visit.VisitRequestDto;
import com.task.faiflywebapi.dto.visit.VisitRespDto;
import com.task.faiflywebapi.dto.visit.VisitResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class VisitControllerImplTests {

  private static final int PAGE = 0;
  private static final int SIZE = 5;
  private static final String PATIENT_NAME = "testPatientName";
  private static final Long DOCTOR_ID_1 = 1L;
  private static final Long PATIENT_ID = 1L;
  private static final Instant START_DATE = Instant.now();
  private static final Instant END_DATE = Instant.now().plusSeconds(5000);
  private static final String PATIENT_LASTNAME = "testPatientLastname";
  private static final String DOCTOR_NAME = "testDoctorName";
  private static final String DOCTOR_LASTNAME = "testDoctorLastName";
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
  private static final DoctorMetaDataResponseDto DOCTOR_META_DATA_RESPONSE_DTO =
      DoctorMetaDataResponseDto.builder()
          .firstName(DOCTOR_NAME)
          .lastName(DOCTOR_LASTNAME)
          .totalPatients(5)
          .build();
  private static final VisitRespDto VISIT_RESP_DTO = VisitRespDto.builder()
      .doctor(DOCTOR_META_DATA_RESPONSE_DTO)
      .startDateTime(START_DATE)
      .endDateTime(END_DATE)
      .build();
  private static final VisitWritePojo VISIT_WRITE_POJO = VisitWritePojo.builder()
      .startDateTime(START_DATE)
      .endDateTime(END_DATE)
      .doctorId(DOCTOR_ID_1)
      .patientId(PATIENT_ID)
      .build();
  private static final VisitRequestDto VISIT_REQUEST_DTO = VisitRequestDto.builder()
      .patientId(String.valueOf(PATIENT_ID))
      .doctorId(String.valueOf(DOCTOR_ID_1))
      .startDateTime(START_DATE)
      .endDateTime(END_DATE)
      .build();
  private static final PatientResponseDto PATIENT_RESPONSE_DTO = PatientResponseDto.builder()
      .firstName(PATIENT_NAME)
      .lastName(PATIENT_LASTNAME)
      .build();
  private static final DoctorResponseDto DOCTOR_RESPONSE_DTO = DoctorResponseDto.builder()
      .firstName(DOCTOR_NAME)
      .lastName(DOCTOR_LASTNAME)
      .build();
  private static final VisitResponseDto VISIT_RESPONSE_DTO = VisitResponseDto.builder()
      .patient(PATIENT_RESPONSE_DTO)
      .doctor(DOCTOR_RESPONSE_DTO)
      .startDateTime(START_DATE)
      .endDateTime(END_DATE)
      .build();

  private MockMvc mockMvc;

  @Mock private VisitMapper visitMapper;
  @Mock private VisitService visitService;
  @InjectMocks
  private VisitControllerImpl visitController;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(visitController)
        .setControllerAdvice(new ApplicationExceptionHandler())
        .build();
  }

  @Test
  public void shouldGetVisitsByPatientNameAndDoctorId() throws Exception {
    when(visitService.getVisits(PAGE, SIZE, PATIENT_NAME, Set.of(DOCTOR_ID_1)))
        .thenReturn(List.of(VISIT_READ_POJO));
    when(visitMapper.toVisitRespDto(VISIT_READ_POJO))
        .thenReturn(VISIT_RESP_DTO);

    final var response = mockMvc.perform(get("/v1/visit")
            .param("search", PATIENT_NAME)
            .param("doctorIds", DOCTOR_ID_1.toString()))
        .andReturn().getResponse();
    assertEquals(HttpStatus.OK.value(), response.getStatus());

    verify(visitService, only())
        .getVisits(PAGE, SIZE, PATIENT_NAME, Set.of(DOCTOR_ID_1));
    verify(visitMapper, atLeast(1))
        .toVisitRespDto(VISIT_READ_POJO);
  }

  @Test
  public void shouldCreateVisitByProvidedData() throws Exception {
    when(visitMapper.toVisitWritePojo(VISIT_REQUEST_DTO))
        .thenReturn(VISIT_WRITE_POJO);
    when(visitService.createVisit(VISIT_WRITE_POJO))
        .thenReturn(VISIT_READ_POJO);
    when(visitMapper.toVisitResponseDto(VISIT_READ_POJO))
        .thenReturn(VISIT_RESPONSE_DTO);

    final var objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    final var requestBody = objectMapper.writeValueAsString(VISIT_REQUEST_DTO);

    final var response = mockMvc.perform(post("/v1/visit")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andReturn().getResponse();
    assertEquals(HttpStatus.OK.value(), response.getStatus());

    verify(visitMapper, times(1))
        .toVisitWritePojo(VISIT_REQUEST_DTO);
    verify(visitService, only())
        .createVisit(VISIT_WRITE_POJO);
    verify(visitMapper, times(1))
        .toVisitResponseDto(VISIT_READ_POJO);
  }
}
