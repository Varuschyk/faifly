package com.task.faiflyapicore.pojo.visit;

import com.task.faiflyapicore.pojo.doctor.DoctorReadPojo;
import com.task.faiflyapicore.pojo.patient.PatientReadPojo;
import lombok.Getter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VisitReadPojo {
  PatientReadPojo patient;
  DoctorReadPojo doctor;
  Instant startDateTime;
  Instant endDateTime;
}
