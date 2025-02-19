package com.task.faiflyapicore.service;

import com.task.faiflyapicore.pojo.visit.VisitReadPojo;
import com.task.faiflyapicore.pojo.visit.VisitWritePojo;
import jakarta.annotation.Nonnull;
import java.util.List;

public interface VisitService {

  List<VisitReadPojo> getVisits(@Nonnull Long patientId);

  VisitReadPojo createVisit(@Nonnull VisitWritePojo visitWritePojo);
}
