package ru.vtb.asciidoc.formats.demo.config;

import static org.asciidoctor.Asciidoctor.Factory.create;

import org.asciidoctor.Asciidoctor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsciiDoctorConfiguration {

  @Bean
  public Asciidoctor asciidoctor() {
    return create();
  }
}
