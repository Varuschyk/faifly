package com.task.faiflyapicore.persistence.repository;

import com.task.faiflyapicore.persistence.enity.VisitEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long> {

  List<VisitEntity> findAllByPatientId(@Nonnull final Long patientId);
}
