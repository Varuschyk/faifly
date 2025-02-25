package com.task.faiflyapicore.pojo.visit;

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
public class VisitWritePojo {
  Long patientId;
  Long doctorId;
  Instant startDateTime;
  Instant endDateTime;
}
