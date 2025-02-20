package com.task.faiflyapicore.mapper.visit;

import com.task.faiflyapicore.mapper.visit.helper.VisitMapperHelper;
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

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {VisitMapperHelper.class})
public interface VisitMapper {

  VisitReadPojo toVisitReadPojo(@Nonnull VisitEntity visitEntity);

  VisitWritePojo toVisitWritePojo(@Nonnull VisitRequestDto visitRequestDto);

  VisitEntity toVisitEntity(@Nonnull VisitWritePojo visitWritePojo);

  VisitResponseDto toVisitResponseDto(@Nonnull VisitReadPojo visitReadPojo);

  @Mapping(target = "doctor", qualifiedByName = "toDoctorMetaDataResponseDto")
  VisitRespDto toVisitRespDto(@Nonnull VisitReadPojo visitReadPojo);
}
