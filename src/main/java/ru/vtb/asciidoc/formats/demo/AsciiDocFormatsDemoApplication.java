package ru.vtb.asciidoc.formats.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.vtb.asciidoc.formats.demo.asciidoc.AsciiDocProcessor;

@SpringBootApplication
public class AsciiDocFormatsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsciiDocFormatsDemoApplication.class, args);
    }
}
