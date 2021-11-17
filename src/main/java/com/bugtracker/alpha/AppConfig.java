package com.bugtracker.alpha;

import com.bugtracker.alpha.dtos.DtoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean(name="dtoConverter")
  public DtoConverter dtoConverter() {
    return new DtoConverter();
  }
  
}
