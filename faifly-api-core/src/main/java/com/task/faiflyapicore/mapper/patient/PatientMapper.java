package com.task.faiflyapicore.mapper.patient;

import com.task.faiflyapicore.persistence.enity.PatientEntity;
import com.task.faiflyapicore.pojo.patient.PatientReadPojo;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {

	PatientReadPojo toPatientReadPojo(@Nonnull PatientEntity patientEntity);
}
