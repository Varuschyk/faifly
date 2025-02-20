package com.task.faiflywebapi.controller;

import com.task.faiflywebapi.dto.visit.VisitRequestDto;
import com.task.faiflywebapi.dto.visit.VisitResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.core.MediaType;

import java.util.Set;

public interface VisitController {

  @GET
  @Path("/visit")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object getVisits(@NotNull @DefaultValue("1") final int page,
                   @NotNull @DefaultValue("5") final int size,
                   @NotNull final String search,
                   @NotNull final Set<Long> doctorIds);

  @POST
  @Path("/visit")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  VisitResponseDto createVisit(@Valid @NotNull final VisitRequestDto visitRequestDto);
}
