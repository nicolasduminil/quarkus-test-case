package fr.simplex_software.quarkus.test_case.tests;

import io.quarkus.test.junit.*;
import io.restassured.response.*;
import org.apache.http.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

@QuarkusTest
public class HostResourceTest
{
  @Test
  public void testHost()
  {
    Response response = given().when().get("/host");
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.prettyPrint()).contains("*** My IP address is");
  }
}
