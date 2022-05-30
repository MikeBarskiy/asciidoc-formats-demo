package ru.vtb.asciidoc.formats.demo.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.asciidoc.formats.demo.service.AsciiDocService;

@RestController
@RequiredArgsConstructor
public class DemoController {

  private final AsciiDocService asciiDocService;

  @GetMapping("/asciidoc/pdf")
  public String pdf() {
    return asciiDocService.generateAsciidoc2Pdf();
  }

  @GetMapping("/html")
  public String html() {
    return asciiDocService.generateAsciidoc2Html();
  }

  @GetMapping("/asciidoc/html")
  public String asciidoc2Html() {
    return asciiDocService.generateAsciidoc2HtmlFromFile();
  }

  @GetMapping("/html/asciidoc")
  public String html2Asciidoc() throws IOException {
    return asciiDocService.generateHtml2Asciidoc();
  }
}
