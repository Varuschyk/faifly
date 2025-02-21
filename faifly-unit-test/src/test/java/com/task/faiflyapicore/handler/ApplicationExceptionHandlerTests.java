package com.task.faiflyapicore.handler;

import com.task.faiflyapicore.configuration.handler.ApplicationExceptionHandler;
import com.task.faiflyapicore.exception.doctor.DoctorNotFoundException;
import com.task.faiflyapicore.exception.patient.PatientNotFoundException;
import com.task.faiflyapicore.exception.visit.VisitBadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationExceptionHandlerTests {

  private static final String BAD_REQUEST_ERROR_MESSAGE = "The request is malformed.";
  private static final String NOT_FOUND_ERROR_MESSAGE = "Can not find the requested resource.";

  private final ApplicationExceptionHandler applicationExceptionHandler = new ApplicationExceptionHandler();
  private final MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

  @Test
  void handleVisitBadRequestException() {
    final var exception = new VisitBadRequestException("Test Exception");

    final var response =
        applicationExceptionHandler.handleBadRequestException(mockHttpServletRequest, exception);

    assertNotNull(response);
    assertEquals(BAD_REQUEST_ERROR_MESSAGE, response.getErrorMessage());
  }

  @Test
  void handlePatientNotFoundException() {
    final var exception = new PatientNotFoundException("Test Exception");

    final var response =
        applicationExceptionHandler.handleNotFoundException(mockHttpServletRequest, exception);

    assertNotNull(response);
    assertEquals(NOT_FOUND_ERROR_MESSAGE, response.getErrorMessage());
  }

  @Test
  void handleDoctorNotFoundException() {
    final var exception = new DoctorNotFoundException("Test Exception");

    final var response =
        applicationExceptionHandler.handleNotFoundException(mockHttpServletRequest, exception);

    assertNotNull(response);
    assertEquals(NOT_FOUND_ERROR_MESSAGE, response.getErrorMessage());
  }
}
