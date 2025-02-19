package com.task.faiflyapicore.mapper.visit;

import com.task.faiflyapicore.persistence.enity.VisitEntity;
import com.task.faiflyapicore.pojo.visit.VisitReadPojo;
import com.task.faiflyapicore.pojo.visit.VisitWritePojo;
import com.task.faiflywebapi.dto.visit.VisitRequestDto;
import com.task.faiflywebapi.dto.visit.VisitRespDto;
import com.task.faiflywebapi.dto.visit.VisitResponseDto;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VisitMapper {

  @Mappings({
      @Mapping(target = "doctorId", source = "doctor.id"),
      @Mapping(target = "patientId", source = "patient.id")
  })
  VisitReadPojo toVisitReadPojo(@Nonnull VisitEntity visitEntity);

  VisitWritePojo toVisitWritePojo(@Nonnull VisitRequestDto visitRequestDto);

  VisitEntity toVisitEntity(@Nonnull VisitWritePojo visitWritePojo);

  VisitResponseDto toVisitResponseDto(@Nonnull VisitReadPojo visitReadPojo);

  VisitRespDto toVisitRespDto(@Nonnull VisitReadPojo visitReadPojo);
}
