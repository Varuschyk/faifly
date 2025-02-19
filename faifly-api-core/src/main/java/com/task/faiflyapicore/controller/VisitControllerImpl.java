package com.task.faiflyapicore.controller;

import com.task.faiflyapicore.mapper.visit.VisitMapper;
import com.task.faiflyapicore.service.PatientService;
import com.task.faiflyapicore.service.VisitService;
import com.task.faiflywebapi.controller.VisitController;
import com.task.faiflywebapi.dto.visit.VisitRequestDto;
import com.task.faiflywebapi.dto.visit.VisitResponseDto;
import com.task.faiflywebapi.dto.visit.VisitsResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@Validated
@RestController
@RequiredArgsConstructor
public class VisitControllerImpl implements VisitController {

  private final VisitService visitService;
  private final PatientService patientService;
  private final VisitMapper visitMapper;

  @Override
  @GetMapping("/v1/visit/{id}")
  public ResponseEntity<VisitsResponse> getVisits(
      @PathVariable("id") @NotNull final Long patientId) {
    final var patient = patientService.get(patientId);
    final var visitsReadPojo = visitService.getVisits(patientId);
    final var visitsResponseDto = visitsReadPojo.stream()
        .map(visitMapper::toVisitRespDto).toList();
    return ResponseEntity.ok(new VisitsResponse(
        patient.getFirstName(), patient.getLastName(), visitsResponseDto));
  }

  @Override
  @PostMapping("/v1/visit")
  public VisitResponseDto createVisit(@Valid @NotNull @RequestBody final VisitRequestDto visitRequestDto) {
    final var visitWritePojo = visitMapper.toVisitWritePojo(visitRequestDto);
    final var createdVisit = visitService.createVisit(visitWritePojo);
    return visitMapper.toVisitResponseDto(createdVisit);
  }
}
