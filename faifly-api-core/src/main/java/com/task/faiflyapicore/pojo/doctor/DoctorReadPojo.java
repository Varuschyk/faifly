package com.task.faiflyapicore.pojo.doctor;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorReadPojo {
  String firstName;
  String lastName;
}
