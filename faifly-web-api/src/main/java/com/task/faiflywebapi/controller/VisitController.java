package com.task.faiflywebapi.controller;

import com.task.faiflywebapi.dto.visit.VisitRequestDto;
import com.task.faiflywebapi.dto.visit.VisitResponseDto;
import com.task.faiflywebapi.dto.visit.VisitsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

public interface VisitController {

  @Operation(summary = "Retrieves the registered patient visits by patientId.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The list of actual patient visits.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = VisitsResponse.class))})})
  @GET
  @Path("/visit/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object getVisits(@NotNull
                   @Parameter(required = true, description = "Patient id that has registered visits.")
                   @PathParam("id") final Long patientId);

  @Operation(summary = "Creates visit for patient to doctor on specified date.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The list of actual patient visits.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = VisitResponseDto.class))})})
  @POST
  @Path("/visit")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  VisitResponseDto createVisit(@Valid @NotNull @RequestBody(required = true)
                               final VisitRequestDto visitRequestDto);
}
