package com.task.faiflyapicore.persistence.repository;

import com.task.faiflyapicore.persistence.enity.VisitEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.Set;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long> {

  int countByDoctorFirstNameAndDoctorLastName(@Nonnull final String firstName,
                                              @Nonnull final String lastName);

  boolean existsByDoctorIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqual(@Nonnull final Long doctorId,
                                                                                      @Nonnull final Instant startDateTime,
                                                                                      @Nonnull final Instant endDateTime);

  Page<VisitEntity> findAllByPatientFirstNameAndDoctorIdIn(@Nonnull final String firstName,
                                                           @Nonnull final Set<Long> doctorIds,
                                                           @Nonnull final Pageable pageable);
}
