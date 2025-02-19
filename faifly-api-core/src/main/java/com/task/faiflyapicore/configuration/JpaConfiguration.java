package com.task.faiflyapicore.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.task.faiflyapicore.persistence.repository")
@EntityScan("com.task")
public class JpaConfiguration {
}
