package com.task.faiflyapicore.persistence.repository;

import com.task.faiflyapicore.persistence.enity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
}
