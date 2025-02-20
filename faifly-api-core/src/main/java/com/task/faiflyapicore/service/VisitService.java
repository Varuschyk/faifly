package com.task.faiflyapicore.service;

import com.task.faiflyapicore.pojo.visit.VisitReadPojo;
import com.task.faiflyapicore.pojo.visit.VisitWritePojo;
import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Set;

public interface VisitService {

  List<VisitReadPojo> getVisits(int page,
                                int size,
                                @Nonnull String patientFirstName,
                                @Nonnull Set<Long> doctorIds);

  VisitReadPojo createVisit(@Nonnull VisitWritePojo visitWritePojo);
}
