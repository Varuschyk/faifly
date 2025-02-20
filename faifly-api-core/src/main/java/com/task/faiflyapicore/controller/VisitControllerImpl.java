package com.task.faiflyapicore.controller;

import com.task.faiflyapicore.mapper.visit.VisitMapper;
import com.task.faiflyapicore.pojo.visit.VisitReadPojo;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Set;

@Validated
@RestController
@RequiredArgsConstructor
public class VisitControllerImpl implements VisitController {

  private final VisitService visitService;
  private final VisitMapper visitMapper;

  @Override
  @GetMapping(value = "/v1/visit", params = {"search", "doctorIds"})
  public ResponseEntity<VisitsResponse> getVisits(@RequestParam(value = "page",
                                                      defaultValue = "0", required = false)
                                                  final int page,
                                                  @RequestParam(value = "size",
                                                      defaultValue = "5", required = false)
                                                  final int size,
                                                  @RequestParam(value = "search")
                                                  final String search,
                                                  @RequestParam("doctorIds")
                                                  final Set<Long> doctorIds) {
    final var visitsReadPojo = visitService.getVisits(page, size, search, doctorIds);
    final var patient = visitsReadPojo.stream()
        .map(VisitReadPojo::getPatient).findFirst()
        .orElse(null);
    final var firstName = patient == null ? null : patient.getFirstName();
    final var lastName = patient == null ? null : patient.getLastName();
    final var visitsResponseDto = visitsReadPojo.stream()
        .map(visitMapper::toVisitRespDto).toList();
    return ResponseEntity.ok(new VisitsResponse(firstName, lastName, visitsResponseDto));
  }

  @Override
  @PostMapping("/v1/visit")
  public VisitResponseDto createVisit(@Valid @NotNull @RequestBody final VisitRequestDto visitRequestDto) {
    final var visitWritePojo = visitMapper.toVisitWritePojo(visitRequestDto);
    final var createdVisit = visitService.createVisit(visitWritePojo);
    return visitMapper.toVisitResponseDto(createdVisit);
  }
}
