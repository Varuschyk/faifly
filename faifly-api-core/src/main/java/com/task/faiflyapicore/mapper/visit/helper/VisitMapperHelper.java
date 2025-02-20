package com.task.faiflyapicore.mapper.visit.helper;

import com.task.faiflyapicore.persistence.repository.VisitRepository;
import com.task.faiflyapicore.pojo.doctor.DoctorReadPojo;
import com.task.faiflywebapi.dto.doctor.DoctorMetaDataResponseDto;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VisitMapperHelper {
  private final VisitRepository visitRepository;

  @Named("toDoctorMetaDataResponseDto")
  @Nonnull
  public DoctorMetaDataResponseDto toDoctorResponseDto(@Nonnull final DoctorReadPojo doctorReadPojo) {
    final var firstName = doctorReadPojo.getFirstName();
    final var lastName = doctorReadPojo.getLastName();
    final var totalPatients = visitRepository.countByDoctorFirstNameAndDoctorLastName(firstName, lastName);
    return DoctorMetaDataResponseDto.builder()
        .firstName(firstName)
        .lastName(lastName)
        .totalPatients(totalPatients)
        .build();
  }
}
