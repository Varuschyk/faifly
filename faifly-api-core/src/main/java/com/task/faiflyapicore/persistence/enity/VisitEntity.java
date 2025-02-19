package com.task.faiflyapicore.persistence.enity;

import jakarta.persistence.*;
import lombok.*;
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
  @OneToOne
  @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
  PatientEntity patient;
  @OneToOne
  @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
  DoctorEntity doctor;
}
