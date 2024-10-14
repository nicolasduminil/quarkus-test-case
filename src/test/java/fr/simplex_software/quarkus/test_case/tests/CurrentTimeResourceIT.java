package fr.simplex_software.quarkus.test_case.tests;

import fr.simplex_software.quarkus.test_case.*;
import io.quarkus.test.junit.*;
import jakarta.inject.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.config.inject.*;
import org.eclipse.microprofile.rest.client.inject.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
public class CurrentTimeResourceIT
{
  @Inject
  @RestClient
  TimeResourceClient timeResourceClient;
  /*@Inject
  @ConfigProperty(name = "base_uri/mp-rest/url")
  String baseURI;*/
  private static final String FMT = "d MMM uuuu, HH:mm:ss XXX z";

  @Test
  public void testCurrentTimeResource() throws IOException
  {
    CompletionStage<Void> currentDateAndTime = timeResourceClient.postCurrentDateAndTime()
      .thenAccept(response ->
      {
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(200);
        String time = response.readEntity(String.class);
        assertThat(time).isNotEmpty();
        assertThat(LocalDateTime.parse(time, DateTimeFormatter.ofPattern(FMT)))
          .isCloseTo(LocalDateTime.now(), byLessThan(1, ChronoUnit.HOURS));
      })
      .exceptionally(ex ->
      {
        ex.printStackTrace();
        return null;
      });
  }
}
