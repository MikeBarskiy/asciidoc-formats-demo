package ru.vtb.asciidoc.formats.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vtb.asciidoc.formats.demo.asciidoc.AsciiDocProcessor;
import ru.vtb.asciidoc.formats.demo.converter.Html2AsciidocConverter;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsciiDocService {

  private final AsciiDocProcessor asciiDocProcessor;

  public String generateAsciidoc2Pdf() {
    File file = new File("docs/sample.adoc");
    return asciiDocProcessor.generatePdf(file);
  }

  public String generateAsciidoc2Html() {
    String content = "Where is my _beer_!";
    return asciiDocProcessor.generateHtml(content);
  }

  public String generateAsciidoc2HtmlFromFile() {
    File file = new File("docs/sample.adoc");
    return asciiDocProcessor.generateHtml(file);
  }

  public String generateHtml2Asciidoc() throws IOException {
    Path htmlPath = Path.of("docs/example.html");
    String htmlContent = Files.readString(htmlPath, StandardCharsets.US_ASCII);

    Html2AsciidocConverter html2AsciidocConverter = new Html2AsciidocConverter();
    String asciiDoc = html2AsciidocConverter.convert(htmlContent);

    return asciiDocProcessor.generateHtml(asciiDoc);
  }

}
