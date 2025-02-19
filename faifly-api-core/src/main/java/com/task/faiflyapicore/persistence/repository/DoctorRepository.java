package com.task.faiflyapicore.persistence.repository;

import com.task.faiflyapicore.persistence.enity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
}
