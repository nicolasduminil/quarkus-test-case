package fr.simplex_software.quarkus.test_case.tests;

import io.quarkus.test.junit.*;
import io.restassured.response.*;
import io.restassured.http.*;
import org.apache.http.*;
import org.junit.jupiter.api.*;

import java.net.*;
import java.nio.charset.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

@QuarkusTest
public class TestCurrentTimeResource
{
  private static final String FMT = "d MMM uuuu, HH:mm:ss XXX z";

  @Test
  public void testCurrentTime()
  {
    Response response = given().when().get("/time");
    assertThat(response).isNotNull();
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
    assertThat(LocalDateTime.parse(response.getBody().prettyPrint(), DateTimeFormatter.ofPattern(FMT)))
      .isCloseTo(LocalDateTime.now(), byLessThan(1, ChronoUnit.HOURS));
  }

  @Test
  public void testCurrentTimeWithZoneId()
  {
    String uri = "/time/" + URLEncoder.encode("Europe/Kaliningrad", StandardCharsets.UTF_8);
    Response response = given().when().get(uri);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(LocalDateTime.parse(response.prettyPrint(), DateTimeFormatter.ofPattern(FMT)))
      .isCloseTo(LocalDateTime.now(), byLessThan(1, ChronoUnit.HOURS));
  }

  @Test
  public void testCurrentTimeWithPost()
  {
    Response response = given().when().post("/time");
    assertThat(response).isNotNull();
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
    assertThat(LocalDateTime.parse(response.getBody().prettyPrint(), DateTimeFormatter.ofPattern(FMT)))
      .isCloseTo(LocalDateTime.now(), byLessThan(1, ChronoUnit.HOURS));
  }
}
