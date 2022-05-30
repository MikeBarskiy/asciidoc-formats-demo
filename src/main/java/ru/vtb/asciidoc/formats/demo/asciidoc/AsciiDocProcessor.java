package ru.vtb.asciidoc.formats.demo.asciidoc;

import java.io.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AsciiDocProcessor {

  private final Asciidoctor asciidoctor;

  public String generatePdf(File pdfFile) {
    Options options = Options.builder()
        .inPlace(true)
        .backend("pdf")
        .build();

    return asciidoctor.convertFile(pdfFile, options);
  }

  public String generateHtml(String content) {
    Options options = Options.builder()
        .inPlace(true)
        .backend("html")
        .build();

    return asciidoctor.convert(content, options);
  }

  public String generateHtml(File htmlFile) {
    Options options = Options.builder()
        .inPlace(true)
        .backend("html")
        .build();

    return asciidoctor.convertFile(htmlFile, options);
  }
}
