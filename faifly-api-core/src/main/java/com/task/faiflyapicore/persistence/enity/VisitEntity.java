package com.task.faiflyapicore.persistence.enity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Entity
@Table(name = "visits")
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class VisitEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;
  @Column(name = "start_date", nullable = false)
  Instant startDateTime;
  @Column(name = "end_date", nullable = false)
  Instant endDateTime;
  @ManyToOne(fetch = FetchType.LAZY)
  PatientEntity patient;
  @ManyToOne(fetch = FetchType.LAZY)
  DoctorEntity doctor;
}
