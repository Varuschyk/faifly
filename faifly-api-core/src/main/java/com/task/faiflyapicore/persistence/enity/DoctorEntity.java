package com.task.faiflyapicore.persistence.enity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.TimeZone;

@Entity
@Table(name = "doctors")
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class DoctorEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;
  @Column(name = "first_name", nullable = false)
  @NotBlank String firstName;
  @Column(name = "last_name", nullable = false)
  @NotBlank String lastName;
  @Column(name = "time_zone", nullable = false)
  @NotNull TimeZone timeZone;
}
