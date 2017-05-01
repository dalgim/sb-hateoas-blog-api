package com.dalgim.example.sb.rest.hateoas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.hateoas.config.EnableHypermediaSupport;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Configuration
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableJpaAuditing
public class AppConfig {

}
