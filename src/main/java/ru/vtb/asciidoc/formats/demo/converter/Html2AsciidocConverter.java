package ru.vtb.asciidoc.formats.demo.converter;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import io.nxnet.html2adoc.ConverterException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Html2AsciidocConverter {

  /**
   * The script engine.
   */
  ScriptEngine scriptEngine;

  /**
   * Instantiates a new html 2 asciidoc converter.
   */
  public Html2AsciidocConverter() {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    this.scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
  }

  /**
   * Instantiates a new html 2 asciidoc converter.
   *
   * @param scriptEngine the script engine
   */
  public Html2AsciidocConverter(ScriptEngine scriptEngine) {
    this.scriptEngine = scriptEngine;
  }

  /**
   * Convert.
   *
   * @param htmlString the html string
   * @return the string
   * @throws ConverterException the converter exception
   */
  public String convert(String htmlString) throws ConverterException {
    /*
     * Create javascript library reader
     */
    Reader jsLibraryReader = new BufferedReader(
        new InputStreamReader(this.getClass().getResourceAsStream("/to-asciidoc.js"),
            StandardCharsets.UTF_8));

    /*
     * Read javascript library
     */
    try {
      this.scriptEngine.eval(jsLibraryReader);
    } catch (ScriptException e) {
      throw new ConverterException(e);
    }

    /*
     * Invoke javascript library method
     */
    Invocable scriptInvocable = (Invocable) this.scriptEngine;
    try {
      WebClient webClient = new WebClient();
      URL url = new URL("http://www.example.com");
      StringWebResponse webResponse = new StringWebResponse(
          "<html><head><title>Test</title></head><body></body></html>", url);
      HtmlPage htmlPage = new HtmlPage(webResponse, webClient.getCurrentWindow());

      return (String) scriptInvocable.invokeFunction("toAsciidoc", htmlString, htmlPage);
    } catch (NoSuchMethodException e) {
      throw new ConverterException(e);
    } catch (ScriptException e) {
      throw new ConverterException(e);
    } catch (MalformedURLException e) {
      throw new ConverterException(e);
    } catch (IOException e) {
      throw new ConverterException(e);
    }
  }
}
